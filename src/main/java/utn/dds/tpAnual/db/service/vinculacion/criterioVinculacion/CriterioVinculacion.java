package utn.dds.tpAnual.db.service.vinculacion.criterioVinculacion;

import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;

import java.util.List;
@Service
public abstract class CriterioVinculacion {

    public abstract RestanteVinculacion ejecutarAlgoritmoVinculacion(List<Ingreso> ingresos, List<Egreso> egresos);
}
