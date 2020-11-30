package utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion;

import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.transaccion.Operacion;
import utn.dds.tpAnual.db.service.vinculacion.reglaVinculacion.ReglaVinculacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Comparator;
import java.util.List;
@Entity(name = "CriterioVinculacionFecha")
@DiscriminatorValue("CriterioVinculacionFecha")
public class CriterioVinculacionFecha extends CriterioVinculacion{

    private static CriterioVinculacionFecha instance;

    public static CriterioVinculacion getInstance(){
        if (instance == null) {
            instance = new CriterioVinculacionFecha();
        }
        return instance;
    }

    public CriterioVinculacionFecha(){

    }

    @Override
    public RestanteVinculacion ejecutarAlgoritmoVinculacion(List<Ingreso> ingresos, List<Egreso> egresos,
                                                            List<ReglaVinculacion> reglas) {
        sortIngresosByDate(ingresos);
        sortEgresosByDate(egresos);
        return vincularListasYaOrdenadasPrimerEgreso(ingresos, egresos, reglas);
    }

    public void sortIngresosByDate (List<Ingreso> operacionesEfectuada){
        operacionesEfectuada.sort(Comparator.comparing(Operacion::getFecha));
    }

    public void sortEgresosByDate (List<Egreso> operacionesEfectuada){
        operacionesEfectuada.sort(Comparator.comparing(Operacion::getFecha));
    }


}
