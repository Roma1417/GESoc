

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 17:39:45
 */
public abstract class CriterioCompra {

	private String nombreCriterio;

	public CriterioCompra(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param presupuestos
	 */
	public abstract Presupuesto getPresupestoQueCumpla(List<Presupuesto> presupuestos);
}//end CriterioCompra