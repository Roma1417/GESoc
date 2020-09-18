package utn.dds.tpAnual.validador;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import utn.dds.tpAnual.builders.DetalleOperacionBuilder;
import utn.dds.tpAnual.builders.DetallePrecioBuilder;
import utn.dds.tpAnual.builders.PresupuestoBuilder;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCompra.CriterioMenorPrecio;
import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.DetallePrecio;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.entity.transaccion.Presupuesto;

import org.junit.Test;

public class CriterioMenorPrecioTest {
	
	private Item mesa = new Item("Mesa");
	
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
