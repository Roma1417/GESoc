package utn.dds.tpAnual.db.service.rules;

import utn.dds.tpAnual.db.dto.transaccion.DetalleOperacionDTO;
import utn.dds.tpAnual.db.dto.transaccion.EgresoDTO;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.transaccion.MedioPago;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.service.business.EgresoResourceBean;
import utn.dds.tpAnual.db.service.jpaService.ItemService;

import java.util.List;
import java.util.Optional;

public class EgresoRules {
    private static final EgresoRules instance = new EgresoRules();

    private EgresoRules(){

    }

    public static EgresoRules getInstance(){
        return instance;
    }

    public void validarVinculacion(Egreso egreso, Ingreso ingreso){
        Entidad entidad = egreso.getEntidadRealizadora();
        if(entidad.getEntidadId() != ingreso.getEntidadRealizadora().getEntidadId()) {
            throw new RuntimeException("El ingreso y el egreso pertenecen a distintas entidades");
        }
        if(!ingreso.satisfaceRestante(egreso)){
            throw new RuntimeException("El monto del egreso es superior al restante del ingreso");
        }
        if(ingreso.getEgresosAsociados().contains(egreso)){
            throw new RuntimeException("El egreso ya se encuentra vinculado al ingreso");
        }
    }

    public void validarCrearEgreso(EgresoDTO egresoDTO, Optional<Proveedor> proveedor, Optional<Entidad> entidadRealizadora,
                                   Optional<Pais> pais, Optional<Moneda> moneda, Optional<MedioPago> medioPago){
        if (!proveedor.isPresent()) {
            throw new RuntimeException("Proveedor no existe");
        }
        if (!entidadRealizadora.isPresent()) {
            throw new RuntimeException("Entidad no existe");
        }
        if (!pais.isPresent()){
            throw new RuntimeException("Pais no existe");
        }
        if (!moneda.isPresent()){
            throw new RuntimeException("Moneda no existe");
        }
        if (!medioPago.isPresent()){
            throw new RuntimeException("Medio de pago no existe");
        }
        if(egresoDTO.getDocumentoComercial().getNumero() == null){
            throw new RuntimeException("Número de documento comercial incompleto");
        }
        if(egresoDTO.getDocumentoComercial().getTipoDocumento() == null){
            throw new RuntimeException("Tipo de documento comercial incompleto");
        }
        if(egresoDTO.getCantidadPresupuestosMinimos() == null){
            throw new RuntimeException("Cantidad presupuestos minimos incompleto");
        }

    }
}
