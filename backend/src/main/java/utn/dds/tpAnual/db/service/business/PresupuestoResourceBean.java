package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.transaccion.DetalleOperacionDTO;
import utn.dds.tpAnual.db.dto.transaccion.DetallePrecioDTO;
import utn.dds.tpAnual.db.dto.transaccion.EgresoDTO;
import utn.dds.tpAnual.db.dto.transaccion.PresupuestoDTO;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.*;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.service.jpaService.*;
import utn.dds.tpAnual.db.service.rules.EgresoRules;
import utn.dds.tpAnual.db.service.rules.PresupuestoRules;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PresupuestoResourceBean {

    @Autowired
    private PresupuestoService presupuestoService;

    @Autowired
    private EgresoService egresoService;

    @Autowired
    private DetalleOperacionService detalleOperacionService;

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private MonedaService monedaService;

    public PresupuestoDTO crearPresupuesto(PresupuestoDTO presupuestoDTO, String username){
        Usuario usuario = usuarioService.getUsuarioByUsername(username);
        Optional<Entidad> entidadRealizadora = entidadService
                .findAllRelated(usuario, presupuestoDTO.getEntidadRealizadora().getIdEntidad());
        List<DetallePrecio> detallesPrecio = getAndFillDetallesPrecio(presupuestoDTO);
        Optional<Pais> pais = paisService
                .findById(presupuestoDTO.getDocumentoComercial().getPais().getPaisId());
        Optional<Moneda> moneda = monedaService
                .findById(presupuestoDTO.getDocumentoComercial().getMoneda().getMonedaId());
        DocumentoComercial documentoComercial = presupuestoDTO.getDocumentoComercial().toEntity(pais.get(), moneda.get());
        Optional<Egreso> egreso = egresoService
                .findFullRelatedById(presupuestoDTO.getEgresoID(), usuario.getUsuarioId());

        PresupuestoRules.getInstance().validarCrearPresupuesto(egreso, presupuestoDTO, detallesPrecio
                , entidadRealizadora, pais, moneda, documentoComercial);

        Presupuesto presupuesto = new Presupuesto(documentoComercial, entidadRealizadora.get()
                , presupuestoDTO.getCodigoOperacion(), detallesPrecio);
        presupuesto.setFecha(LocalDate.now());
        this.presupuestoService.save(presupuesto);
        PresupuestoDTO presupuestoDTOResultado = new PresupuestoDTO().from(presupuesto);
        presupuestoDTOResultado.setEgresoID(egreso.get().getOperacionId());
        return presupuestoDTOResultado;
    }

    public PageableResponse<PresupuestoDTO, Presupuesto> getPresupuestos(PageableRequest pageableRequest, String categoria) {
        Page<Presupuesto> presupuestos = presupuestoService.findAllRelatedByCategoria(pageableRequest, categoria);
        return new PageableResponse().fromPage(presupuestos, new PresupuestoDTO());
    }

    private List<DetallePrecio> getAndFillDetallesPrecio(PresupuestoDTO presupuesto) {
        List<DetallePrecio> detalles = new ArrayList<>();

        HashMap<Long, DetallePrecioDTO> detallesPrecioHashMap = new HashMap<>();
        presupuesto.getDetallesPrecio().forEach(detallePrecioDTO -> {
            detallesPrecioHashMap.put(detallePrecioDTO.getDetalleOperacion().getDetalleOperacionId(), detallePrecioDTO);
        });
        List<DetalleOperacion> detallesOperaciones = detalleOperacionService.findAllByIds(detallesPrecioHashMap.keySet());
        if (detallesOperaciones.size() != detallesPrecioHashMap.size()) {
            throw new ValidationException("No se encontraron todos detalles de operaciones");
        }

        detallesOperaciones.stream().forEach(detalleOperacion -> {
            DetallePrecioDTO detallePrecioDTO = detallesPrecioHashMap.get(detalleOperacion.getDetalleOperacionId());
            if (detallePrecioDTO == null) {
                throw new ValidationException("Datos inválidos");
            }
            detalles.add(new DetallePrecio(detalleOperacion, detallePrecioDTO.getPrecio()));
        });
        return detalles;
    }
}
