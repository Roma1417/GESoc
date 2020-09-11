package utn.dds.tpAnual.db.service.vinculacion.criterioVinculacion;

import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CriterioVinculacionMix extends CriterioVinculacion{

    private List<CriterioVinculacion> criteriosVinculacion;

    @Override
    public RestanteVinculacion ejecutarAlgoritmoVinculacion(List<Ingreso> ingresos, List<Egreso> egresos) {
        AtomicReference<RestanteVinculacion> restanteVinculacion = new AtomicReference<>(new RestanteVinculacion(ingresos, egresos));
        if (criteriosVinculacion != null){
            criteriosVinculacion.forEach(criterioVinculacion -> {
                restanteVinculacion.set(criterioVinculacion
                        .ejecutarAlgoritmoVinculacion(restanteVinculacion.get().getIngresoList(),
                                restanteVinculacion.get().getEgresoList()));
            });
        }
        return restanteVinculacion.get();
    }

    public List<CriterioVinculacion> getCriteriosVinculacion() {
        return criteriosVinculacion;
    }

    public void setCriteriosVinculacion(List<CriterioVinculacion> criteriosVinculacion) {
        this.criteriosVinculacion = criteriosVinculacion;
    }
}
