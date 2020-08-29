package utn.dds.tpAnual.transaccion;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
public class DetallePrecio {

	private DetalleOperacion detalleOperacion;
	private Float precio;

	/**
	 * 
	 * @param detalleOperacion
	 * @param precio
	 */
	public DetallePrecio(DetalleOperacion detalleOperacion, Float precio){
		this.detalleOperacion = detalleOperacion;
		this.precio = precio;
	}
	
	public Float getPrecio() {
		return precio;
	}
	
	public DetalleOperacion getDetalleOperacion() {
		return detalleOperacion;
	}
	
	public Float getPrecioTotal() {
		return precio * detalleOperacion.getCantidad();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("\ndetalleOperacion", detalleOperacion)
			    .append("\nprecio", precio)
			    .toString();
	}
}
