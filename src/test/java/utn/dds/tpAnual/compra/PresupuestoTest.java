package utn.dds.tpAnual.compra;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Arrays;

import org.junit.Test;

import utn.dds.tpAnual.transaccion.DetalleOperacion;
import utn.dds.tpAnual.transaccion.DetallePrecio;
import utn.dds.tpAnual.transaccion.Item;
import utn.dds.tpAnual.transaccion.Presupuesto;

public class PresupuestoTest {
	
	private Item mesa = new Item(1L,"Mesa");
	
	private DetalleOperacion unDetalleOperacion = new DetalleOperacion(mesa, 15F, 3);
	private DetalleOperacion otroDetalleOperacion = new DetalleOperacion(mesa, 20F, 4);
	
	private DetallePrecio unDetallePrecio = new DetallePrecio(unDetalleOperacion, 10F);
	private DetallePrecio otroDetallePrecio = new DetallePrecio(otroDetalleOperacion, 12F);
	
	private List<DetallePrecio> listaDetalles = Arrays.asList(unDetallePrecio, otroDetallePrecio);
	private List<DetallePrecio> listaDetallesNula;
	private List<DetallePrecio> listaDetallesVacia = Arrays.asList();
	
	private Presupuesto presupuestoConListaCargada = new Presupuesto(null, null, 1, listaDetalles);
	private Presupuesto presupuestoConListaNula = new Presupuesto(null, null, 2, listaDetallesNula);
	private Presupuesto presupuestoConListaVacia = new Presupuesto(null, null, 3, listaDetallesVacia);
	
	@Test
	public void precioTotalDelPresupuestoConListaCargada(){
		assertEquals(78F, presupuestoConListaCargada.getTotal(), 0F);
	}
	
	@Test
	public void precioTotalDelPresupuestoConListaNula(){
		assertEquals(0F, presupuestoConListaNula.getTotal(), 0F);
	}
	
	@Test
	public void precioTotalDelPresupuestoConListaVacia(){
		assertEquals(0F, presupuestoConListaVacia.getTotal(), 0F);
	}
	
	@Test
	public void precioTotalDelDetalle() {
		assertEquals(30F, unDetallePrecio.getPrecioTotal(), 0);
	}
}
