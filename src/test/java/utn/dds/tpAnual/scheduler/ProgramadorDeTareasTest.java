package utn.dds.tpAnual.scheduler;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utn.dds.tpAnual.builders.EgresoBuilder;
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
	private static ProgramadorDeTareas programador;
				
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
		Egreso egresoSinPresupuestos = new EgresoBuilder().buildEgresoSinPresupuestos();
		Egreso egresoCumplidor = new EgresoBuilder().buildEgresoCumplidor();
		EgresosObserver observer = EgresosObserver.getInstance();
		observer.agregarEgreso(egresoSinPresupuestos);
		observer.agregarEgreso(egresoCumplidor);
		TimeUnit.SECONDS.sleep(20);
		assertEquals(observer.cantidadEgresos(), 0);
    }
}
