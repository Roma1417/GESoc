package utn.dds.tpAnual.validador;

import java.util.List;

import utn.dds.tpAnual.compra.Presupuesto;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
public class CriterioMenorPrecio extends CriterioCompra {

	public CriterioMenorPrecio(){

	}

	/**
	 * 
	 * @param presupuestos
	 */
	@Override
	public Presupuesto getPresupuestoQueCumpla(List<Presupuesto> presupuestos){
		Presupuesto presupuestoMenorPrecio;
		
		if (presupuestos == null || presupuestos.size() == 0) {
			presupuestoMenorPrecio = null;
			} else {
				presupuestoMenorPrecio = presupuestos.get(0);
				
				for(Presupuesto presupuesto : presupuestos) {	
					presupuestoMenorPrecio = menorPrecio(presupuestoMenorPrecio, presupuesto);
				}
			}
		return presupuestoMenorPrecio;
	}
	
	private Presupuesto menorPrecio(Presupuesto unPresupuesto, Presupuesto otroPresupuesto){
		return unPresupuesto.getTotal() < otroPresupuesto.getTotal() ? unPresupuesto : otroPresupuesto;
	}
	

	
	
	
}