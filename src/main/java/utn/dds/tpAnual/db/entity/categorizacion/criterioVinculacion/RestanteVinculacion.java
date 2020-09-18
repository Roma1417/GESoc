package utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion;

import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;

import java.util.List;

public class RestanteVinculacion {

    private List<Ingreso> ingresoList;
    private List<Egreso> egresoList;

    public RestanteVinculacion(List<Ingreso> ingresoList, List<Egreso> egresoList) {
        this.ingresoList = ingresoList;
        this.egresoList = egresoList;
    }

    public List<Ingreso> getIngresoList() {
        return ingresoList;
    }

    public void setIngresoList(List<Ingreso> ingresoList) {
        this.ingresoList = ingresoList;
    }

    public List<Egreso> getEgresoList() {
        return egresoList;
    }

    public void setEgresoList(List<Egreso> egresoList) {
        this.egresoList = egresoList;
    }
}
