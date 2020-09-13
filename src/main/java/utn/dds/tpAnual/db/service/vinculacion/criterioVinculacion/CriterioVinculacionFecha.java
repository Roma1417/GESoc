package utn.dds.tpAnual.db.service.vinculacion.criterioVinculacion;

import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
@Entity(name = "CriterioVinculacionFecha")
@DiscriminatorValue("CriterioVinculacionFecha")
public class CriterioVinculacionFecha extends CriterioVinculacion{

    @Override
    public RestanteVinculacion ejecutarAlgoritmoVinculacion(List<Ingreso> ingreso, List<Egreso> egresos) {
        return null;
    }
}
