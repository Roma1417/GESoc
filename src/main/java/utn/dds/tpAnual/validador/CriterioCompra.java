package utn.dds.tpAnual.validador;

import java.util.List;

import utn.dds.tpAnual.compra.Presupuesto;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
public abstract class CriterioCompra {

	private String nombreCriterio;

	public CriterioCompra() {
		
	}
	
	/**
	 * 
	 * @param presupuestos
	 */
	public abstract Presupuesto getPresupuestoQueCumpla(List<Presupuesto> presupuestos);
}