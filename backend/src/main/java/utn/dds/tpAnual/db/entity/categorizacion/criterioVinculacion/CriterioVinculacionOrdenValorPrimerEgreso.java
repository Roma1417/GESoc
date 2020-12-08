package utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion;

import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.transaccion.Operacion;
import utn.dds.tpAnual.db.service.vinculacion.reglaVinculacion.ReglaVinculacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Comparator;
import java.util.List;

@Entity(name = "OrdenValorPrimerEgreso")
@DiscriminatorValue("OrdenValorPrimerEgreso")
public class CriterioVinculacionOrdenValorPrimerEgreso extends CriterioVinculacion{

    private static CriterioVinculacionOrdenValorPrimerEgreso instance;

    public static CriterioVinculacion getInstance(){
        if (instance == null) {
            instance = new CriterioVinculacionOrdenValorPrimerEgreso();
        }
        return instance;
    }

    public CriterioVinculacionOrdenValorPrimerEgreso(){

    }

    @Override
    public RestanteVinculacion ejecutarAlgoritmoVinculacion(List<Ingreso> ingresos, List<Egreso> egresos,
                                                            List<ReglaVinculacion> reglas) {
        sortIngresosByTotal(ingresos);
        sortEgresosByTotal(egresos);
        return vincularListasYaOrdenadasPrimerEgreso(ingresos, egresos, reglas);
    }

}
