package utn.dds.tpAnual.compra;

import java.util.List;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class Presupuesto extends Operacion {

	private List<DetallePrecio> detalles;

	public Presupuesto(){

	}
	/**
	 * 
	 * @param detalles
	 */
	public Presupuesto(List<DetallePrecio> detalles){

	}

	public Float getTotal(){
		return null;
	}
	
	public List<DetallePrecio> getDetalles(){
		return detalles;
	}
}