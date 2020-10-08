package utn.dds.tpAnual.db.service.rules;

import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.service.business.EgresoResourceBean;

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
}
