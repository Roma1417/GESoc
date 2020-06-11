package utn.dds.tpAnual.afip.scheduler;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utn.dds.tpAnual.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.transaccion.DetalleOperacion;
import utn.dds.tpAnual.transaccion.DetallePrecio;
import utn.dds.tpAnual.transaccion.Egreso;
import utn.dds.tpAnual.transaccion.Item;
import utn.dds.tpAnual.transaccion.Presupuesto;
import utn.dds.tpAnual.usuario.Usuario;
import utn.dds.tpAnual.validador.CriterioMenorPrecio;
import utn.dds.tpAnual.validador.EgresosObserver;
import utn.dds.tpAnual.validador.ValidadorEgreso;

public class ProgramadorDeTareasTest{
	//ValidadorEgreso
	private ValidadorEgreso validadorTest = ValidadorEgreso.getInstance();
	private EgresosObserver observer;
	private static ProgramadorDeTareas programador;
		
	//DetalleOperacion
	private Item itemTest = new Item(123L, "itemTest");
	private DetalleOperacion unDetalleOperacion = new DetalleOperacion(itemTest, 10F, 3);
	private DetalleOperacion otroDetalleOperacion = new DetalleOperacion(itemTest, 20F, 4);
	private List<DetalleOperacion> unosDetallesOperacion = Arrays.asList(unDetalleOperacion, unDetalleOperacion);
	private List<DetalleOperacion> otrosDetallesOperacion = Arrays.asList(unDetalleOperacion, otroDetalleOperacion);
		
	//DetallePrecio
	private DetallePrecio unDetallePrecio = new DetallePrecio(unDetalleOperacion, 10F);
	private DetallePrecio otroDetallePrecio = new DetallePrecio(otroDetalleOperacion, 12F);
	private List<DetallePrecio> unosDetallesPrecio = Arrays.asList(unDetallePrecio, unDetallePrecio);
	private List<DetallePrecio> otrosDetallesPrecio = Arrays.asList(unDetallePrecio, otroDetallePrecio);
		
	//Presupuesto
	private Presupuesto unPresupuesto = new Presupuesto(null, null, 1782, unosDetallesPrecio);
	private Presupuesto otroPresupuesto = new Presupuesto(null, null, 1723, otrosDetallesPrecio);
	private List<Presupuesto> listaPresupuestosVacia;
	private List<Presupuesto> unaListaPresupuestos = Arrays.asList(unPresupuesto, unPresupuesto);
	private List<Presupuesto> otraListaPresupuestos = Arrays.asList(otroPresupuesto, otroPresupuesto);
	private List<Presupuesto> listaVariosPresupuestos = Arrays.asList(unPresupuesto, unPresupuesto, otroPresupuesto, otroPresupuesto);

	//Criterio Compra
	private CriterioMenorPrecio criterioMenorPrecio = CriterioMenorPrecio.getInstance();
		
	//Egreso
	private Usuario unRevisor = new Usuario("unRevisor", "asndihg382");
	private Usuario otroRevisor = new Usuario("otroRevisor", "wuiefnwi471");
	private List<Usuario> revisoresTest = Arrays.asList(unRevisor, otroRevisor);
		
	private Egreso egresoSinPresupuestos = new Egreso(null, null, 542, null, null, null, 2, criterioMenorPrecio, listaPresupuestosVacia, null, revisoresTest);
	private Egreso egresoCumplidor = new Egreso(null, null, 87, unosDetallesOperacion, null, null, 2, criterioMenorPrecio, unaListaPresupuestos, null, revisoresTest);
		
	@BeforeClass
	public static void beforeAll() throws Exception {
		programador = ProgramadorDeTareas.getInstance();
		programador.iniciar();
	}
	@AfterClass
	public static void afterAll() throws Exception {
		programador.stop();
	}
	
	@Test
	public void programadorDeTareasValidaEgresosProcesados() throws InterruptedException {
		EgresosObserver observer = EgresosObserver.getInstance();
		observer.agregarEgreso(egresoSinPresupuestos);
		observer.agregarEgreso(egresoCumplidor);
		TimeUnit.SECONDS.sleep(20);
		assertEquals(observer.cantidadEgresos(), 0);
    }
}
