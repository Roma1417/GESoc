package utn.dds.tpAnual.scheduler;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import utn.dds.tpAnual.builders.EgresoBuilder;
import utn.dds.tpAnual.criterioCompra.ValidadorEgreso;
import utn.dds.tpAnual.transaccion.Egreso;
import utn.dds.tpAnual.validador.EgresosObserver;

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
		observer.notificar(egresoSinPresupuestos);
		observer.notificar(egresoCumplidor);
		TimeUnit.SECONDS.sleep(4);
		assertEquals(ValidadorEgreso.getInstance().cantidadEgresos(), 0);
    }
}
