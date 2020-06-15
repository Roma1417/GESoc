package utn.dds.tpAnual.validador;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import utn.dds.tpAnual.afip.builders.EgresoBuilder;
import utn.dds.tpAnual.transaccion.*;
import utn.dds.tpAnual.usuario.Mensaje;
import utn.dds.tpAnual.usuario.Usuario;
import utn.dds.tpAnual.validador.CriterioMenorPrecio;
import utn.dds.tpAnual.validador.ValidadorEgreso;

public class ValidadorEgresoTest {
	
	//ValidadorEgreso
	private ValidadorEgreso validadorTest = ValidadorEgreso.getInstance();	

	@Test
	public void egresoSinPresupuestosMinimos() {
		Egreso egresoSinPresupuestosMinimos = EgresoBuilder.buildEgresoSinPresupuestosMinimos();
		assertTrue(validadorTest.validarEgreso(egresoSinPresupuestosMinimos));
	}
	
	@Test
	public void egresoInvalidoPorFaltaDePresupuestos() {
		Egreso egresoSinPresupuestos = EgresoBuilder.buildEgresoSinPresupuestos();
		assertFalse(validadorTest.validarEgreso(egresoSinPresupuestos));
	}	
	
	@Test
	public void egresoConDetallesDeDistintoTamanio() {
		Egreso egresoConDetallesDeDistintoTamanio = EgresoBuilder.buildEgresoConDetallesDeDistintoTamanio();
		assertFalse(validadorTest.validarEgreso(egresoConDetallesDeDistintoTamanio));
	}
	
	@Test
	public void egresoConDistintosPrecios() {
		Egreso egresoNoBasadoEnPresupuesto = EgresoBuilder.buildEgresoNoBasadoEnPresupuesto();
		assertFalse(validadorTest.validarEgreso(egresoNoBasadoEnPresupuesto));
	}
	
	@Test
	public void egresoInvalidoConNotificacionDeFallo() {
		Egreso egresoSinPresupuestos = EgresoBuilder.buildEgresoSinPresupuestos();
		Usuario unRevisor = EgresoBuilder.getUnRevisor();
		Usuario otroRevisor = EgresoBuilder.getOtroRevisor();
		validadorTest.validarEgreso(egresoSinPresupuestos);
		List<Mensaje> mensajeUsuario = unRevisor.getBandejaMensajes();
		List<Mensaje> mensajeOtroUsuario = otroRevisor.getBandejaMensajes();
		assertTrue("Fallo de Validacion".equals(mensajeUsuario.get(0).getCuerpo())
					&& "Fallo de Validacion".equals(mensajeOtroUsuario.get(0).getCuerpo()));
	}

	@Test
	public void egresoValidoConNotificacionDeExito() {
		Egreso egresoCumplidor = EgresoBuilder.buildEgresoCumplidor();
		Usuario unRevisor = EgresoBuilder.getUnRevisor();
		Usuario otroRevisor = EgresoBuilder.getOtroRevisor();
		boolean validez = validadorTest.validarEgreso(egresoCumplidor);
		List<Mensaje> mensajeUsuario = unRevisor.getBandejaMensajes();
		List<Mensaje> mensajeOtroUsuario = otroRevisor.getBandejaMensajes();
		assertTrue(validez && "Validacion realizada con Exito".equals(mensajeUsuario.get(1).getCuerpo())
							&& "Validacion realizada con Exito".equals(mensajeOtroUsuario.get(1).getCuerpo()));
	}
	
}

