package utn.dds.tpAnual.validador;

import java.util.ArrayList;
import java.util.List;

import utn.dds.tpAnual.compra.DetalleOperacion;
import utn.dds.tpAnual.compra.DetallePrecio;
import utn.dds.tpAnual.compra.Item;
import utn.dds.tpAnual.compra.Presupuesto;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class CriterioMenorPrecioTest {
	
Item mesa = new Item(1L,"Mesa");
	
	DetalleOperacion detalleOperacion1 = new DetalleOperacion(mesa, 15F, 3);
	DetalleOperacion detalleOperacion2 = new DetalleOperacion(mesa, 20F, 4);
	
	DetallePrecio detallePrecio1 = new DetallePrecio(detalleOperacion1, 10F);
	DetallePrecio detallePrecio2 = new DetallePrecio(detalleOperacion2, 12F);
	
	private List<DetallePrecio> detalles1 = new ArrayList();
	private List<DetallePrecio> detalles2 = new ArrayList();
	
	private List<DetallePrecio> cargarListaDetalles(List<DetallePrecio> detalles) {
		detalles.add(detallePrecio1);
		detalles.add(detallePrecio2);
		return detalles;
	}
	
	Presupuesto presupuesto1 = new Presupuesto(cargarListaDetalles(detalles1));
	Presupuesto presupuesto2 = new Presupuesto(cargarListaDetalles(cargarListaDetalles(detalles2)));
	
	private List<Presupuesto> presupuestos = new ArrayList();
	
	private List<Presupuesto> cargarListaPresupuestos(List<Presupuesto> presupuestos){
		presupuestos.add(presupuesto1);
		presupuestos.add(presupuesto2);
		return presupuestos;
	}
	
	
	private CriterioMenorPrecio criterioMenorPrecio = new CriterioMenorPrecio();
	
	@Test 
	public void obtengoPresupuestoDeMenorPrecio() {
		assertEquals(criterioMenorPrecio.getPresupuestoQueCumpla(cargarListaPresupuestos(presupuestos)),presupuesto1);
		
	}
	
	@Test
	public void precioTotalDelPresupuesto1() {
		assertEquals(78F, presupuesto1.getTotal(),0F);
	}
	
	@Test
	public void precioTotalDelPresupuesto2() {
		assertEquals(156F, presupuesto2.getTotal(),0F);
	}
	
}
