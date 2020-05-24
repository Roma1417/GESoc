package utn.dds.tpAnual.compra;



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
	
}//end DetalleOperacion