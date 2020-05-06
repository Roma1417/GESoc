package utn.dds.tpAnual.validador;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import utn.dds.tpAnual.compra.*;
import utn.dds.tpAnual.usuario.Mensaje;
import utn.dds.tpAnual.usuario.Usuario;

public class ValidadorTest {
	
	//Validador
	private Validador validadorTest = Validador.getInstance();
	
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
	

	@Test
	public void egresoSinPresupuestosMinimos() {
		Egreso egresoSinPresupuestosMinimos = new Egreso(null, null, 123, null, null, null, 0, null, null, null, null);
		assertTrue(validadorTest.validarEgreso(egresoSinPresupuestosMinimos));
	}
	
	@Test
	public void egresoInvalidoPorFaltaDePresupuestos() {
		assertFalse(validadorTest.validarEgreso(egresoSinPresupuestos));
	}	
	
	@Test
	public void egresoConDetallesDeDistintoTamanio() {
		Egreso egresoConDetallesDeDistintoTamanio = new Egreso(null, null, 475, unosDetallesOperacion, null, null, 2, null, listaVariosPresupuestos, null, null);
		assertFalse(validadorTest.validarEgreso(egresoConDetallesDeDistintoTamanio));
	}
	
	@Test
	public void egresoConDistintosPrecios() {
		Egreso egresoNoBasadoEnPresupuesto = new Egreso(null, null, 345, otrosDetallesOperacion, null, null, 2, null, otraListaPresupuestos, null, null);
		assertFalse(validadorTest.validarEgreso(egresoNoBasadoEnPresupuesto));
	}
	
	@Test
	public void egresoInvalidoConNotificacionDeFallo() {
		validadorTest.validarEgreso(egresoSinPresupuestos);
		List<Mensaje> mensajeUsuario = unRevisor.getBandejaMensajes();
		List<Mensaje> mensajeOtroUsuario = otroRevisor.getBandejaMensajes();
		assertTrue("Fallo de Validacion".equals(mensajeUsuario.get(0).getCuerpo())
					&& "Fallo de Validacion".equals(mensajeOtroUsuario.get(0).getCuerpo()));
	}

	@Test
	public void egresoValidoConNotificacionDeExito() {
		boolean validez = validadorTest.validarEgreso(egresoCumplidor);
		List<Mensaje> mensajeUsuario = unRevisor.getBandejaMensajes();
		List<Mensaje> mensajeOtroUsuario = otroRevisor.getBandejaMensajes();
		assertTrue(validez && "Validacion realizada con Exito".equals(mensajeUsuario.get(0).getCuerpo())
							&& "Validacion realizada con Exito".equals(mensajeOtroUsuario.get(0).getCuerpo()));
	}
	
}

