package utn.dds.tpAnual.builders;

import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.Item;

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

    public DetalleOperacionBuilder mockDetalle() {
	    this.cantidad = 5;
	    this.precio = 10F;
	    this.item = new Item("Un item");
	    return this;
    }
}
