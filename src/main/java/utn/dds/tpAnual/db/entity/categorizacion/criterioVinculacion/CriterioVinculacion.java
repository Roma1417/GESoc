package utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion;
import utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion.RestanteVinculacion;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.service.vinculacion.reglaVinculacion.ReglaVinculacion;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "CRITERIO_VINCULACION")
public abstract class CriterioVinculacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Long criterioId;

    public abstract RestanteVinculacion ejecutarAlgoritmoVinculacion(List<Ingreso> ingresos, List<Egreso> egresos,
                                                                     List<ReglaVinculacion> reglas);
}
