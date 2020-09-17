package utn.dds.tpAnual.db.service.validador;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.EgresoBuilder;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.service.EgresoService;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidadorEgresoTest {

    @Autowired
	private ValidadorEgreso validador;

    @Autowired
    private EgresoService egresoService;

	@Test
	public void egresoSinPresupuestosMinimos() {
		Egreso egresoSinPresupuestosMinimos = new EgresoBuilder().buildEgresoSimple();
		egresoService.save(egresoSinPresupuestosMinimos);
		validador.validarEgresos();
		Egreso egresoObtenido = egresoService.getEgresoById(egresoSinPresupuestosMinimos.getOperacionId());
		assertTrue(egresoObtenido.getResultadoValidacion().);
	}

	@Test
	public void egresoInvalidoPorFaltaDePresupuestos() {
		Egreso egresoSinPresupuestos = new EgresoBuilder().buildEgresoSinPresupuestos();
		egresoService.save(egresoSinPresupuestos);
		Egreso egresoObtenido = egresoService.getEgresoById(egresoSinPresupuestos.getOperacionId());
		assertFalse(validador.validarEgreso(egresoObtenido));
	}	

	@Test
	public void egresoConDetallesDeDistintoTamanio() {
		Egreso egresoConDetallesDeDistintoTamanio = new EgresoBuilder().buildEgresoConDetallesDeDistintoTamanio();
		egresoService.save(egresoConDetallesDeDistintoTamanio);
		Egreso egresoObtenido = egresoService.getEgresoById(egresoConDetallesDeDistintoTamanio.getOperacionId());
		assertFalse(validador.validarEgreso(egresoObtenido));
	}

	@Test
	public void egresoConDistintosPrecios() {
		Egreso egresoNoBasadoEnPresupuesto = new EgresoBuilder().buildEgresoNoBasadoEnPresupuesto();
		egresoService.save(egresoNoBasadoEnPresupuesto);
		Egreso egresoObtenido = egresoService.getEgresoById(egresoNoBasadoEnPresupuesto.getOperacionId());
		assertFalse(validador.validarEgreso(egresoObtenido));
	}

	@Test
	public void egresoInvalidoConNotificacionDeFallo() {
		Egreso egresoSinPresupuestos = new EgresoBuilder().buildEgresoSinPresupuestos();
		Usuario unRevisor = (Usuario) egresoSinPresupuestos.getRevisores().stream().findAny().get();
		validador.validarEgreso(egresoSinPresupuestos);
		Set<Mensaje> mensajeUsuario = unRevisor.getBandejaMensajes();
		assertTrue("Fallo de Validacion".equals(mensajeUsuario.stream().findAny().get().getCuerpo()));
	}

	@Test
	public void egresoValidoConNotificacionDeExito() {
		Egreso egresoCumplidor = new EgresoBuilder().buildEgresoCumplidor();
		Usuario unRevisor = egresoCumplidor.getRevisores().stream().findAny().get();
		boolean validez = validador.validarEgreso(egresoCumplidor);
		Set<Mensaje> mensajesUsuario = unRevisor.getBandejaMensajes();
		assertTrue(validez && "Validacion realizada con Exito"
				.equals(mensajesUsuario.stream().findAny().get().getCuerpo()));
	}

}

