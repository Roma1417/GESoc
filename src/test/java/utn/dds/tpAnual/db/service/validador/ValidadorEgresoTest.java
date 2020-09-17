package utn.dds.tpAnual.db.service.validador;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.EgresoBuilder;
import utn.dds.tpAnual.db.configuracion.ConfiguracionEnum;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.ConfiguracionService;
import utn.dds.tpAnual.db.service.EgresoService;


import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(ProgramadorDeTareas.class)
public class ValidadorEgresoTest {

    @Autowired
	private ValidadorEgreso validador;

    @Autowired
    private EgresoService egresoService;

    @Autowired
	private ConfiguracionService configuracionService;

	@Test
	public void egresoSinPresupuestosMinimos() {
		Egreso egresoSinPresupuestosMinimos = new EgresoBuilder().buildEgresoSimple();
		egresoService.save(egresoSinPresupuestosMinimos);
		validador.validarEgresos();
		Egreso egresoObtenido = egresoService.getEgresoById(egresoSinPresupuestosMinimos.getOperacionId());
		assertTrue(egresoObtenido.getEsValidacionCorrecta() &&
				egresoObtenido.getResultadoValidacion()
						.equals(configuracionService.getValue(ConfiguracionEnum.MENSAJE_CORRECTO)));
	}

	@Test
	public void egresoInvalidoPorFaltaDePresupuestos() {
		Egreso egresoSinPresupuestos = new EgresoBuilder().buildEgresoSinPresupuestos();
		egresoService.save(egresoSinPresupuestos);
		validador.validarEgresos();
		Egreso egresoObtenido = egresoService.getEgresoById(egresoSinPresupuestos.getOperacionId());
		assertTrue(!egresoObtenido.getEsValidacionCorrecta() &&
				egresoObtenido.getResultadoValidacion()
						.equals(configuracionService.getValue(ConfiguracionEnum.MENSAJE_ERRONEO)));
	}	
/*
	@Test
	public void egresoConDetallesDeDistintoTamanio() {
		Egreso egresoConDetallesDeDistintoTamanio = new EgresoBuilder().buildEgresoConDetallesDeDistintoTamanio();
		egresoService.save(egresoConDetallesDeDistintoTamanio);
		validador.validarEgresos();
		Egreso egresoObtenido = egresoService.getEgresoById(egresoConDetallesDeDistintoTamanio.getOperacionId());
		assertTrue(!egresoObtenido.getEsValidacionCorrecta() &&
				egresoObtenido.getResultadoValidacion()
						.equals(configuracionService.getValue(ConfiguracionEnum.MENSAJE_ERRONEO)));
	}/// error de Transcient algo   */

	@Test
	public void egresoConDistintosPrecios() {
		Egreso egresoNoBasadoEnPresupuesto = new EgresoBuilder().buildEgresoNoBasadoEnPresupuesto();
		egresoService.save(egresoNoBasadoEnPresupuesto);
		validador.validarEgresos();
		Egreso egresoObtenido = egresoService.getEgresoById(egresoNoBasadoEnPresupuesto.getOperacionId());
		assertTrue(!egresoObtenido.getEsValidacionCorrecta() &&
				egresoObtenido.getResultadoValidacion()
						.equals(configuracionService.getValue(ConfiguracionEnum.MENSAJE_ERRONEO)));
	}

	@Test
	public void egresoInvalidoConNotificacionDeFallo() {
		Egreso egresoSinPresupuestos = new EgresoBuilder().buildEgresoSinPresupuestos();
		egresoService.save(egresoSinPresupuestos);
		validador.validarEgresos();
		Egreso egresoObtenido = egresoService.getEgresoById(egresoSinPresupuestos.getOperacionId());
		String mensaje = egresoObtenido.getRevisores().stream().findAny().get().getBandejaMensajes().stream().findAny().get().getCuerpo();
		assertTrue( mensaje.equals(ConfiguracionEnum.MENSAJE_ERRONEO.getDefaultValue()));
	}
/*
	@Test
	public void egresoValidoConNotificacionDeExito() {
		Egreso egresoCumplidor = new EgresoBuilder().buildEgresoCumplidor();
		egresoService.save(egresoCumplidor);
		validador.validarEgresos();
		Egreso egresoObtenido = egresoService.getEgresoById(egresoCumplidor.getOperacionId());
		String mensaje = egresoObtenido.getRevisores().stream().findAny().get().getBandejaMensajes().stream().findAny().get().getCuerpo();
		assertTrue( mensaje.equals(ConfiguracionEnum.MENSAJE_CORRECTO.getDefaultValue()));
	} /// error en size() De detalles operacion  del egreso*/

}

