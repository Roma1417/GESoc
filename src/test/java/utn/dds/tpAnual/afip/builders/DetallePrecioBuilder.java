package utn.dds.tpAnual.afip.builders;

import utn.dds.tpAnual.transaccion.DetalleOperacion;
import utn.dds.tpAnual.transaccion.DetallePrecio;

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
