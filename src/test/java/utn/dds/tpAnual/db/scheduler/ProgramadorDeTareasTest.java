package utn.dds.tpAnual.db.scheduler;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.EgresoBuilder;
import utn.dds.tpAnual.criterioCompra.ValidadorEgreso;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.TestHibernateSpring;
import utn.dds.tpAnual.transaccion.Egreso;
import utn.dds.tpAnual.validador.EgresosObserver;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={TestHibernateSpring.class})
public class ProgramadorDeTareasTest{
	@Test
	public void programadorDeTareasValidaEgresosProcesados() throws InterruptedException {
		Egreso egresoSinPresupuestos = new EgresoBuilder().buildEgresoSinPresupuestos();
		Egreso egresoCumplidor = new EgresoBuilder().buildEgresoCumplidor();
		EgresosObserver observer = EgresosObserver.getInstance();
		observer.notificar(egresoSinPresupuestos);
		observer.notificar(egresoCumplidor);
		TimeUnit.SECONDS.sleep(3);
		assertEquals(ValidadorEgreso.getInstance().cantidadEgresos(), 0);
    }
}
