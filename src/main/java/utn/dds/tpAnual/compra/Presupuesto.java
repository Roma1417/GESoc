package utn.dds.tpAnual.compra;

import java.util.List;

import utn.dds.tpAnual.entidad.Entidad;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class Presupuesto extends Operacion {

	private List<DetallePrecio> detallesPrecio;
	
	public Presupuesto(DocumentoComercial documentoComercial, Entidad entidadRealizadora, List<DetallePrecio> detallesPrecio) {
		super(documentoComercial, entidadRealizadora);
		this.detallesPrecio = detallesPrecio;
	}

	public Float getTotal(){
		Float total = 0F;

		if (detalles != null) {
			for(DetallePrecio detalle : detalles) {
				total += detalle.getPrecioTotal();
			}
		}	
		return total;
	}
	
	public List<DetallePrecio> getDetallesPrecio(){
		return detallesPrecio;
	}
}