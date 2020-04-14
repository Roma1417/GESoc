package utn.dds.tpAnual.compra;

import java.util.List;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class Presupuesto extends Operacion {

	private List<DetallePrecio> detalles;

	public Presupuesto(List<DetallePrecio> detalles){
		this.detalles = detalles;
	}
	/**
	 * 
	 * @param detalles
	 */
	

	public Float getTotal(){
		
		float total = 0;
		
		for(int x=0 ; x<detalles.size() ; x++) {
		total += detalles.get(x).getPrecioTotal();
		}	
		
		return total;
	}
	
	
	
	
}