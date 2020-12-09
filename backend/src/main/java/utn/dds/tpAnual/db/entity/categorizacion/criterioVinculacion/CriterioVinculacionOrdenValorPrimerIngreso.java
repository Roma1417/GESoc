package utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion;

import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.transaccion.Operacion;
import utn.dds.tpAnual.db.service.vinculacion.reglaVinculacion.ReglaVinculacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Comparator;
import java.util.List;

@Entity(name = "OrdenValorPrimerIngreso")
@DiscriminatorValue("OrdenValorPrimerIngreso")
public class CriterioVinculacionOrdenValorPrimerIngreso extends CriterioVinculacion{

    private static CriterioVinculacionOrdenValorPrimerIngreso instance;

    public static CriterioVinculacion getInstance(){
        if (instance == null) {
            instance = new CriterioVinculacionOrdenValorPrimerIngreso();
        }
        return instance;
    }

    public CriterioVinculacionOrdenValorPrimerIngreso(){

    }

    @Override
    public RestanteVinculacion ejecutarAlgoritmoVinculacion(List<Ingreso> ingresos, List<Egreso> egresos,
                                                            List<ReglaVinculacion> reglas) {
        sortIngresosByTotal(ingresos);
        sortEgresosByTotal(egresos);
        return vincularListasYaOrdenadasPrimerIngreso(ingresos, egresos, reglas);
    }

}
