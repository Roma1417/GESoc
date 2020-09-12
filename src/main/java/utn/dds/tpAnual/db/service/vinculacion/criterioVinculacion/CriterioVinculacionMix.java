package utn.dds.tpAnual.db.service.vinculacion.criterioVinculacion;

import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import java.util.List;

public class CriterioVinculacionMix extends CriterioVinculacion{

    private List<CriterioVinculacion> criteriosVinculacion;

    @Override
    public RestanteVinculacion ejecutarAlgoritmoVinculacion(List<Ingreso> ingresos, List<Egreso> egresos) {
        RestanteVinculacion restanteVinculacion = new RestanteVinculacion(ingresos, egresos);
        if (criteriosVinculacion != null){
            for (CriterioVinculacion criterioVinculacion : criteriosVinculacion){
                restanteVinculacion = criterioVinculacion
                        .ejecutarAlgoritmoVinculacion(restanteVinculacion.getIngresoList(),
                                restanteVinculacion.getEgresoList());
                if (restanteVinculacion.getEgresoList().isEmpty() || restanteVinculacion.getIngresoList().isEmpty()){
                    return restanteVinculacion;
                }
            }
        }
        return restanteVinculacion;
    }

    public List<CriterioVinculacion> getCriteriosVinculacion() {
        return criteriosVinculacion;
    }

    public void setCriteriosVinculacion(List<CriterioVinculacion> criteriosVinculacion) {
        this.criteriosVinculacion = criteriosVinculacion;
    }
}
