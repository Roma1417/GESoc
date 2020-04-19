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
		Float total = 0F;
		
		if (detalles != null && detalles.size() != 0) {
			for(DetallePrecio detalle : detalles) {
				total += detalle.getPrecioTotal();
			}
		}	
		return total;
}

	
	
	
}