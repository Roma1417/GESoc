package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.transaccion.EgresoDTO;
import utn.dds.tpAnual.db.dto.complex.VinculacionEgresoIngresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.transaccion.*;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.service.jpaService.*;
import utn.dds.tpAnual.db.service.rules.EgresoRules;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EgresoResourceBean {

    @Autowired
    private EgresoService egresoService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private MonedaService monedaService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PresupuestoResourceBean presupuestoResourceBean;

    @Autowired
    private UsuarioService usuarioService;

    public PageableResponse<EgresoDTO, Egreso> getEgresos(PageableRequest pageableRequest, String categoria, String username) {
        Page<Egreso> egresos = egresoService.findAllRelatedByCategoria(pageableRequest, categoria, username);
        return new PageableResponse().fromPage(egresos, new EgresoDTO());
    }

    public EgresoDTO crearEgreso(EgresoDTO egresoDTO, String username){
        Usuario usuario = usuarioService.getUsuarioByUsername(username);
        Optional<Proveedor> proveedor = proveedorService.findById(egresoDTO.getProveedor().getIdProveedor());
        Optional<Entidad> entidadRealizadora = entidadService.findAllRelated(usuario, egresoDTO.getEntidadRealizadora().getIdEntidad());
        Optional<Pais> pais = paisService.findById(egresoDTO.getDocumentoComercial().getPais().getPaisId());
        Optional<Moneda> moneda = monedaService.findById(egresoDTO.getDocumentoComercial().getMoneda().getMonedaId());
        Optional<MedioPago> medioPago = medioPagoService.findById(egresoDTO.getMedioPago().getIdMedioPago());
        DocumentoComercial documentoComercial = egresoDTO.getDocumentoComercial().toEntity(pais.get(), moneda.get());
        List<DetalleOperacion> detallesOperacion = getAndFillDetalles(egresoDTO);
        Integer codigoOperacion = egresoDTO.getCodigoOperacion();

        EgresoRules.getInstance().validarCrearEgreso(egresoDTO, proveedor, entidadRealizadora, pais, moneda, medioPago);
        List<Presupuesto> presupuestos = createPresupuesto(egresoDTO);

        Egreso egreso = new Egreso(documentoComercial, entidadRealizadora.get(), codigoOperacion,
        detallesOperacion, LocalDate.now(), medioPago.get(),egresoDTO.getCantidadPresupuestosMinimos(),
                null, presupuestos, proveedor.get(), null);
        egreso.setFechaOperacion(egresoDTO.getFechaOperacion());

        egresoService.save(egreso);
        egresoDTO.setIdEgreso(egreso.getOperacionId());
        return egresoDTO;
    }

    private List<Presupuesto> createPresupuesto(EgresoDTO egresoDTO) {
        if (egresoDTO.getPresupuestos() != null && !egresoDTO.getPresupuestos().isEmpty()) {
           return egresoDTO
                   .getPresupuestos()
                   .stream()
                   .map(presupuestoDTO -> presupuestoResourceBean.crearPresupuesto(presupuestoDTO))
                   .collect(Collectors.toList());
        }
        return null;
    }

    private List<DetalleOperacion> getAndFillDetalles(EgresoDTO egresoDTO) {
        List<DetalleOperacion> detalles = new ArrayList<>();
        egresoDTO.getDetalles().forEach(detalleOperacion -> {
            Optional<Item> item = itemService.findById(detalleOperacion.getItem().getId());
            if (!item.isPresent()) {
                throw new RuntimeException("El item " + detalleOperacion.getItem().getId() + " no existe");
            }
            detalles.add(new DetalleOperacion(item.get(),
                                detalleOperacion.getPrecio(),
                    detalleOperacion.getCantidad()));
        });
        return detalles;
    }

    public void vincular(VinculacionEgresoIngresoDTO vinculacion) {
        Optional<Egreso> egresoOptional = egresoService.findFullById(vinculacion.getEgresoId());
        Optional<Ingreso> ingresoOptional = ingresoService.findFullById(vinculacion.getIngresoId());
        if (!egresoOptional.isPresent() || !ingresoOptional.isPresent()){
            throw new RuntimeException("Los registros no fueron encontrados");
        }
        Egreso egreso = egresoOptional.get();
        Ingreso ingreso = ingresoOptional.get();
        EgresoRules.getInstance().validarVinculacion(egreso, ingreso);
        ingreso.vincularEgreso(egreso);
        egresoService.save(egreso);
    }
}
