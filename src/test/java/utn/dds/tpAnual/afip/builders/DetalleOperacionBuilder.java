package utn.dds.tpAnual.afip.builders;

import utn.dds.tpAnual.transaccion.DetalleOperacion;
import utn.dds.tpAnual.transaccion.Item;

public class DetalleOperacionBuilder {
	private int cantidad;
	private Item item;
	private Float precio;
	
	public DetalleOperacionBuilder withCantidad(int cantidad){
        this.cantidad = cantidad;
        return this;
    }
	public DetalleOperacionBuilder withItem(Item item){
        this.item = item;
        return this;
    }
	public DetalleOperacionBuilder withPrecio(Float precio){
        this.precio = precio;
        return this;
    }
    public DetalleOperacion build(){
        return new DetalleOperacion(item, precio, cantidad);
    }
}
