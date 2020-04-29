package utn.dds.tpAnual.compra;

import static org.junit.Assert.*;

import org.junit.Test;

public class OperacionEfectuadaTest {

	private Egreso egresoTest = new Egreso(null, null, 0, null, null, null, 10, null, null, null, null);
	
    @Test
    public void getTotalDeOperacionEfectuadaSiNoHayDetalles() {
    	assertTrue(egresoTest.getTotal().equals(0F));
    }

    @Test
    public void getTotalDeOperacionEfectuada() {
    	Item itemTest = new Item(0L,"itemTest");
    	DetalleOperacion d1 = new DetalleOperacion(itemTest, 50F, 3); // 150
    	DetalleOperacion d2 = new DetalleOperacion(itemTest, 10F, 4); // 40
    	DetalleOperacion d3 = new DetalleOperacion(itemTest, 100F, 2); //200
    	
    	egresoTest.addDetalleOperacion(d1);
    	egresoTest.addDetalleOperacion(d2);
    	egresoTest.addDetalleOperacion(d3);
    	
    	assertTrue(egresoTest.getTotal().equals(390F));
    }
    
}
