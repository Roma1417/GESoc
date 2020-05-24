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

	public Presupuesto(DocumentoComercial documentoComercial, Entidad entidadRealizadora, int codigoOperacion,
			List<DetallePrecio> detallesPrecio) {
		super(documentoComercial, entidadRealizadora, codigoOperacion);
		this.detallesPrecio = detallesPrecio;
	}
	
	public Float getTotal(){
		Float total = 0F;

		if (detallesPrecio != null) {
			for(DetallePrecio detalle : detallesPrecio) {
				total += detalle.getPrecioTotal();
			}
		}	
		return total;
	}
	
	public List<DetallePrecio> getDetallesPrecio(){
		return detallesPrecio;
	}
}