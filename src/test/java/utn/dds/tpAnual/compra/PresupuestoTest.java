package utn.dds.tpAnual.compra;

import static org.junit.Assert.assertEquals;


import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

public class PresupuestoTest {
	
	Item mesa = new Item(1L,"Mesa");
	
	DetalleOperacion detalleOperacion1 = new DetalleOperacion(mesa, 15F, 3);
	DetalleOperacion detalleOperacion2 = new DetalleOperacion(mesa, 20F, 4);
	
	DetallePrecio detallePrecio1 = new DetallePrecio(detalleOperacion1, 10F);
	DetallePrecio detallePrecio2 = new DetallePrecio(detalleOperacion2, 12F);
	
	private List<DetallePrecio> detalles = new ArrayList();
	
	private List<DetallePrecio> cargarLista(List<DetallePrecio> detalles) {
		detalles.add(detallePrecio1);
		detalles.add(detallePrecio2);
		return detalles;
	}
	
	Presupuesto presupuesto = new Presupuesto(cargarLista(detalles));
	
	@Test
	public void precioTotalDelPresupuesto() {
		assertEquals(78F, presupuesto.getTotal(),0F);
	}
	
	@Test
	public void precioTotalDelDetalle() {
		assertEquals(30F, detallePrecio1.getPrecioTotal(),0);
	}
	
}
