package utn.dds.tpAnual.db.service.vinculacion.criterioVinculacion;

import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;

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

    public abstract RestanteVinculacion ejecutarAlgoritmoVinculacion(List<Ingreso> ingresos, List<Egreso> egresos);
}
