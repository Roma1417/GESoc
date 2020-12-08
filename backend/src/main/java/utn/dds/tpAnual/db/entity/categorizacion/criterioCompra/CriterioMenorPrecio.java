package utn.dds.tpAnual.db.entity.categorizacion.criterioCompra;

import java.util.List;

import utn.dds.tpAnual.db.entity.transaccion.Presupuesto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
@Entity(name = "CriterioMenorPrecio")
@DiscriminatorValue("CriterioMenorPrecio")
public class CriterioMenorPrecio extends CriterioCompra {

	private final String nombreCriterio = "Criterio de menor precio";

	private static CriterioMenorPrecio instance = new CriterioMenorPrecio();
	
	private CriterioMenorPrecio() {
		
	}
	
	public static CriterioMenorPrecio getInstance() {
		return instance;
	}

	@Override
	public Presupuesto getPresupuestoQueCumpla(List<Presupuesto> presupuestos){
		Presupuesto presupuestoQueCumple = null;

		if (presupuestos != null && presupuestos.size() > 0){
			presupuestoQueCumple = presupuestos.get(0);
			for(Presupuesto presupuesto : presupuestos) {	
				presupuestoQueCumple = presupuestoDeMenorPrecio(presupuestoQueCumple, presupuesto);
			}
		}
		return presupuestoQueCumple;
	}

	private Presupuesto presupuestoDeMenorPrecio(Presupuesto unPresupuesto, Presupuesto otroPresupuesto){
		return unPresupuesto.getTotal() < otroPresupuesto.getTotal() ? unPresupuesto : otroPresupuesto;
	}

}