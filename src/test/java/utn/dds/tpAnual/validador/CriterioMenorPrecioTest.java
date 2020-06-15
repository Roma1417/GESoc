package utn.dds.tpAnual.validador;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import utn.dds.tpAnual.afip.builders.DetalleOperacionBuilder;
import utn.dds.tpAnual.afip.builders.DetallePrecioBuilder;
import utn.dds.tpAnual.afip.builders.PresupuestoBuilder;
import utn.dds.tpAnual.transaccion.DetalleOperacion;
import utn.dds.tpAnual.transaccion.DetallePrecio;
import utn.dds.tpAnual.transaccion.Item;
import utn.dds.tpAnual.transaccion.Presupuesto;
import utn.dds.tpAnual.validador.CriterioMenorPrecio;

import org.junit.Test;

public class CriterioMenorPrecioTest {
	
	private Item mesa = new Item(1L, "Mesa");
	
	private DetalleOperacion unDetalleOperacion = new DetalleOperacionBuilder()
			.withItem(mesa).withPrecio(15F).withCantidad(3).build();
	private DetalleOperacion otroDetalleOperacion = new DetalleOperacionBuilder()
			.withItem(mesa).withPrecio(20F).withCantidad(4).build();
	
	private DetallePrecio unDetallePrecio = new DetallePrecioBuilder()
			.withDetalleOperacion(unDetalleOperacion).withPrecio(10F).build();
	private DetallePrecio otroDetallePrecio = new DetallePrecioBuilder()
			.withDetalleOperacion(otroDetalleOperacion).withPrecio(12F).build();
		
	private Presupuesto unPresupuesto = new PresupuestoBuilder()
			.withCodigoOperacion(1)
			.withDetallePrecio(unDetallePrecio).withDetallePrecio(otroDetallePrecio).build();
	private Presupuesto otroPresupuesto = new PresupuestoBuilder()
			.withCodigoOperacion(2)
			.withDetallePrecio(unDetallePrecio).withDetallePrecio(unDetallePrecio)
			.withDetallePrecio(otroDetallePrecio).withDetallePrecio(otroDetallePrecio).build();
	
	private List<Presupuesto> listaPresupuestos = Arrays.asList(unPresupuesto, otroPresupuesto);
	private List<Presupuesto> listaPresupuestosNula;
	private List<Presupuesto> listaSinPresupuestos = Arrays.asList(); 
	
	private CriterioMenorPrecio criterioMenorPrecio = CriterioMenorPrecio.getInstance();
	
	@Test 
	public void obtengoPresupuestoDeMenorPrecio(){
		assertEquals(criterioMenorPrecio.getPresupuestoQueCumpla(listaPresupuestos),unPresupuesto);
	}
	
	@Test 
	public void obtengoNullSobreUnaListaNula(){
		assertEquals(criterioMenorPrecio.getPresupuestoQueCumpla(listaPresupuestosNula),null);
	}
	
	@Test 
	public void obtengoNullSobreUnaListaVacia(){
		assertEquals(criterioMenorPrecio.getPresupuestoQueCumpla(listaSinPresupuestos),null);
	}
}
