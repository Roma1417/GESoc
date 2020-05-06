package utn.dds.tpAnual.validador;

import java.util.List;

import utn.dds.tpAnual.compra.Presupuesto;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
public class CriterioMenorPrecio extends CriterioCompra {
	
	private static CriterioMenorPrecio instance = new CriterioMenorPrecio();
	
	private CriterioMenorPrecio() {
		
	}
	
	public static CriterioMenorPrecio getInstance() {
		return instance;
	}
	

	/**
	 * 
	 * @param presupuestos
	 */
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