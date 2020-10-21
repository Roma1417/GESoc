package utn.dds.tpAnual.compra;

import static org.junit.Assert.*;

import org.junit.Test;

import utn.dds.tpAnual.builders.DetalleOperacionBuilder;
import utn.dds.tpAnual.builders.EgresoBuilder;
import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Item;

public class OperacionEfectuadaTest {

    @Test
    public void getTotalDeOperacionEfectuada() {
    	Item itemTest = new Item( null);
    	DetalleOperacion d1 = new DetalleOperacionBuilder()
    			.withItem(itemTest).withPrecio(50F).withCantidad(3).build();
    	DetalleOperacion d2 = new DetalleOperacionBuilder()
    			.withItem(itemTest).withPrecio(10F).withCantidad(4).build();
    	DetalleOperacion d3 = new DetalleOperacionBuilder()
    			.withItem(itemTest).withPrecio(100F).withCantidad(2).build();
    	Egreso egreso = new EgresoBuilder()
    			.withDetalleOperacion(d1)
    			.withDetalleOperacion(d2)
    			.withDetalleOperacion(d3)
    			.build();    	
    	assertTrue(egreso.getTotal().equals(390F));
    }
    
    @Test
    public void getTotalDeOperacionEfectuadaSiNoHayDetalles() {
    	Egreso egreso = new EgresoBuilder().build();
    	assertTrue(egreso.getTotal().equals(0F));
    }
}
