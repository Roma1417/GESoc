package utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion;
import utn.dds.tpAnual.db.entity.EntityInterface;
import utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion.RestanteVinculacion;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.transaccion.Operacion;
import utn.dds.tpAnual.db.service.vinculacion.reglaVinculacion.ReglaVinculacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "CRITERIO_VINCULACION")
public abstract class CriterioVinculacion implements EntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Long criterioId;

    public abstract RestanteVinculacion ejecutarAlgoritmoVinculacion(List<Ingreso> ingresos, List<Egreso> egresos,
                                                                     List<ReglaVinculacion> reglas);

    public Long getCriterioId() {
        return criterioId;
    }

    public void setCriterioId(Long criterioId) {
        this.criterioId = criterioId;
    }


    private boolean cumpleReglas (Ingreso ingreso, Egreso egreso, List<ReglaVinculacion> reglas){
        return reglas.stream().allMatch(reglaVinculacion -> reglaVinculacion.puedeVincularse(ingreso, egreso));
    }

    protected RestanteVinculacion vincularListasYaOrdenadasPrimerEgreso(List<Ingreso> ingresos, List<Egreso> egresos,
                                                                        List<ReglaVinculacion> reglas){
        List<Egreso> egresosARemover = new ArrayList<>();
        for (Ingreso ingreso : ingresos){
            for (Egreso egreso : egresos) {
                if (cumpleReglas(ingreso, egreso, reglas) &&
                        ingreso.satisfaceRestante(egreso)) {
                    ingreso.vincularEgreso(egreso);
                    egresosARemover.add(egreso);
                }
            }
            egresos.removeAll(egresosARemover);
            egresosARemover.clear();
        }
        return new RestanteVinculacion(ingresos, egresos);
    }

    protected RestanteVinculacion vincularListasYaOrdenadasPrimerIngreso(List<Ingreso> ingresos, List<Egreso> egresos,
                                                                         List<ReglaVinculacion> reglas){
        List<Egreso> egresosARemover = new ArrayList<>();
        for (Egreso egreso : egresos) {
            for (Ingreso ingreso : ingresos){
                if (cumpleReglas(ingreso, egreso, reglas) &&
                        ingreso.satisfaceRestante(egreso)) {
                    ingreso.vincularEgreso(egreso);
                    egresosARemover.add(egreso);
                    break;
                }
            }
        }
        egresos.removeAll(egresosARemover);
        return new RestanteVinculacion(ingresos, egresos);
    }

    protected void sortIngresosByTotal (List<Ingreso> operacionesEfectuada){
        operacionesEfectuada.sort(Comparator.comparing(Operacion::getTotal));
    }

    protected void sortEgresosByTotal (List<Egreso> operacionesEfectuada){
        operacionesEfectuada.sort(Comparator.comparing(Operacion::getTotal));
    }

    @Override
    public Long getId() {
        return getCriterioId();
    }

}
