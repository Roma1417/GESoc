package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.transaccion.DetalleOperacionDTO;
import utn.dds.tpAnual.db.dto.transaccion.EgresoDTO;
import utn.dds.tpAnual.db.dto.complex.VinculacionEgresoIngresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.transaccion.MedioPago;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.service.jpaService.*;
import utn.dds.tpAnual.db.service.rules.EgresoRules;

import java.util.List;
import java.util.Optional;

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

    public PageableResponse<EgresoDTO, Egreso> getEgresos(PageableRequest pageableRequest, String categoria) {
        return null;
    }

    public EgresoDTO crearEgreso(EgresoDTO egresoDTO, Usuario usuario){
        List<DetalleOperacion> detallesOperacion = DetalleOperacionDTO.toListStatic(egresoDTO.getDetalles());
        Optional<Proveedor> proveedor = proveedorService.findById(egresoDTO.getProveedor().getIdProveedor());
        Optional<Entidad> entidadRealizadora = entidadService.findAllRelated(usuario, egresoDTO.getEntidadRealizadora().getIdEntidad());
        Optional<Pais> pais = paisService.findById(egresoDTO.getDocumentoComercial().getPais().getIdPais());
        Optional<Moneda> moneda = monedaService.findById(egresoDTO.getDocumentoComercial().getMoneda().getIdMoneda());
        Optional<MedioPago> medioPago = medioPagoService.findById(egresoDTO.getMedioPago().getIdMedioPago());


        return null;
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
