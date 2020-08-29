package utn.dds.tpAnual.builders;

import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.DetallePrecio;

public class DetallePrecioBuilder {
	private DetalleOperacion detalleOperacion;
	private Float precio;
	
	public DetallePrecioBuilder withPrecio(Float precio){
        this.precio = precio;
        return this;
    }
	public DetallePrecioBuilder withDetalleOperacion(DetalleOperacion detalleOperacion){
        this.detalleOperacion = detalleOperacion;
        return this;
    }
    public DetallePrecio build(){
        return new DetallePrecio(detalleOperacion, precio);
    }
}
