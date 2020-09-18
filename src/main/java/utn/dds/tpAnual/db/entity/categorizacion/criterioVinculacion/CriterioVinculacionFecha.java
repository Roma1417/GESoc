package utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion;

import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.service.vinculacion.reglaVinculacion.ReglaVinculacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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

    private CriterioVinculacionFecha(){

    }

    @Override
    public RestanteVinculacion ejecutarAlgoritmoVinculacion(List<Ingreso> ingreso, List<Egreso> egresos,
                                                            List<ReglaVinculacion> reglas) {
        return null;
    }
}
