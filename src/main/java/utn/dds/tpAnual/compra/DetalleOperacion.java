package utn.dds.tpAnual.compra;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
public class DetalleOperacion {

	private int cantidad;
	private Item item;
	private Float precio;

	/**
	 * 
	 * @param item
	 * @param precio
	 * @param cantidad
	 */
	public DetalleOperacion(Item item, Float precio, int cantidad){
		this.item = item;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
    public Float getPrecio() {
		return precio;
	}
    
	public Float getTotal() {
		return cantidad * precio;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("\nitem", item)
			    .append("\nprecio", precio)
			    .append("\ncantidad", cantidad)
			    .toString();
	}
	
}
