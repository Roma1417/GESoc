package utn.dds.tpAnual.db.service.validador;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.EgresoBuilder;
import utn.dds.tpAnual.db.configuracion.ConfiguracionEnum;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.ConfiguracionService;
import utn.dds.tpAnual.db.service.jpaService.EgresoService;


import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@MockBean(ProgramadorDeTareas.class)
@DataJpaTest(showSql=false)
@DirtiesContext
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
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

}

