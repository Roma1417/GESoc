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
		
Presupuesto presupuestoMenorPrecio = presupuestos.get(0);
		
		for(int x = 1 ; x < presupuestos.size() ; x=x+1) {
			presupuestoMenorPrecio = menorPrecio(presupuestoMenorPrecio, presupuestos.get(x));
		}
		return presupuestoMenorPrecio;
	}
	
	private Presupuesto menorPrecio(Presupuesto presupuestoA, Presupuesto presupuestoB) { 
		if (presupuestoA.getTotal() < presupuestoB.getTotal()) 
		return presupuestoA;
		else return presupuestoB;
	}
	

	
	
	
}