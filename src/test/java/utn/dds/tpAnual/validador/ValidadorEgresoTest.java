package utn.dds.tpAnual.validador;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import utn.dds.tpAnual.builders.EgresoBuilder;
import utn.dds.tpAnual.criterioCompra.ValidadorEgreso;
import utn.dds.tpAnual.db.entity.transaccion.*;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.entity.usuario.Usuario;

public class ValidadorEgresoTest {
	
	//ValidadorEgreso
	private ValidadorEgreso validadorTest = ValidadorEgreso.getInstance();	

	@Test
	public void egresoSinPresupuestosMinimos() {
		Egreso egresoSinPresupuestosMinimos = new EgresoBuilder().buildEgresoSimple();
		assertTrue(validadorTest.validarEgreso(egresoSinPresupuestosMinimos));
	}
	
	@Test
	public void egresoInvalidoPorFaltaDePresupuestos() {
		Egreso egresoSinPresupuestos = new EgresoBuilder().buildEgresoSinPresupuestos();
		assertFalse(validadorTest.validarEgreso(egresoSinPresupuestos));
	}	
	
	@Test
	public void egresoConDetallesDeDistintoTamanio() {
		Egreso egresoConDetallesDeDistintoTamanio = new EgresoBuilder().buildEgresoConDetallesDeDistintoTamanio();
		assertFalse(validadorTest.validarEgreso(egresoConDetallesDeDistintoTamanio));
	}
	
	@Test
	public void egresoConDistintosPrecios() {
		Egreso egresoNoBasadoEnPresupuesto = new EgresoBuilder().buildEgresoNoBasadoEnPresupuesto();
		assertFalse(validadorTest.validarEgreso(egresoNoBasadoEnPresupuesto));
	}
	
	@Test
	public void egresoInvalidoConNotificacionDeFallo() {
		Egreso egresoSinPresupuestos = new EgresoBuilder().buildEgresoSinPresupuestos();
		Usuario unRevisor = egresoSinPresupuestos.getRevisores().get(0);
		Usuario otroRevisor = egresoSinPresupuestos.getRevisores().get(1);
		validadorTest.validarEgreso(egresoSinPresupuestos);
		List<Mensaje> mensajeUsuario = unRevisor.getBandejaMensajes();
		List<Mensaje> mensajeOtroUsuario = otroRevisor.getBandejaMensajes();
		assertTrue("Fallo de Validacion".equals(mensajeUsuario.get(0).getCuerpo())
					&& "Fallo de Validacion".equals(mensajeOtroUsuario.get(0).getCuerpo()));
	}

	@Test
	public void egresoValidoConNotificacionDeExito() {
		Egreso egresoCumplidor = new EgresoBuilder().buildEgresoCumplidor();
		Usuario unRevisor = egresoCumplidor.getRevisores().get(0);
		Usuario otroRevisor = egresoCumplidor.getRevisores().get(1);
		boolean validez = validadorTest.validarEgreso(egresoCumplidor);
		List<Mensaje> mensajesUsuario = unRevisor.getBandejaMensajes();
		List<Mensaje> mensajesOtroUsuario = otroRevisor.getBandejaMensajes();
		assertTrue(validez && "Validacion realizada con Exito".equals(mensajesUsuario.get(0).getCuerpo())
							&& "Validacion realizada con Exito".equals(mensajesOtroUsuario.get(0).getCuerpo()));
	}
	
}

