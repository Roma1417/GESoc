package utn.dds.tpAnual.compra;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Arrays;

import org.junit.Test;

import utn.dds.tpAnual.afip.builders.DetalleOperacionBuilder;
import utn.dds.tpAnual.afip.builders.DetallePrecioBuilder;
import utn.dds.tpAnual.afip.builders.PresupuestoBuilder;
import utn.dds.tpAnual.transaccion.DetalleOperacion;
import utn.dds.tpAnual.transaccion.DetallePrecio;
import utn.dds.tpAnual.transaccion.Item;
import utn.dds.tpAnual.transaccion.Presupuesto;

public class PresupuestoTest {
	
	private Item mesa = new Item(1L,"Mesa");
	
	private DetalleOperacion unDetalleOperacion = new DetalleOperacionBuilder()
			.withItem(mesa).withPrecio(15F).withCantidad(3).build();
	private DetalleOperacion otroDetalleOperacion = new DetalleOperacionBuilder()
			.withItem(mesa).withPrecio(20F).withCantidad(4).build();
	
	private DetallePrecio unDetallePrecio = new DetallePrecioBuilder()
			.withDetalleOperacion(unDetalleOperacion).withPrecio(10F).build();
	private DetallePrecio otroDetallePrecio = new DetallePrecioBuilder()
			.withDetalleOperacion(otroDetalleOperacion).withPrecio(12F).build();
	
	private List<DetallePrecio> listaDetallesNula;
	private List<DetallePrecio> listaDetallesVacia = Arrays.asList();
	
	private Presupuesto presupuestoConListaCargada = new PresupuestoBuilder()
			.withDetallePrecio(unDetallePrecio)
			.withDetallePrecio(otroDetallePrecio)
			.build();
	
	private Presupuesto presupuestoConListaNula = new PresupuestoBuilder()
			.withListaDetallesPrecio(listaDetallesNula)
			.build();
	private Presupuesto presupuestoConListaVacia = new PresupuestoBuilder()
			.withListaDetallesPrecio(listaDetallesVacia)
			.build();
	
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
