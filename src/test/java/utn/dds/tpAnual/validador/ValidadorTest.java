package utn.dds.tpAnual.validador;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import utn.dds.tpAnual.compra.*;
import utn.dds.tpAnual.entidad.Entidad;
import utn.dds.tpAnual.proveedor.Proveedor;
import utn.dds.tpAnual.usuario.Mensaje;
import utn.dds.tpAnual.usuario.Usuario;

public class ValidadorTest {
	
	//Validador
	private Validador validadorTest = new Validador();
	
	//Operacion
	private DocumentoComercial documentoComercialTest;
	private Entidad entidadRealizadora;
	
	//DetalleOperacion
	private Item itemTest = new Item();
	private DetalleOperacion unDetalleOperacion = new DetalleOperacion(itemTest, 10F, 3);
	private DetalleOperacion otroDetalleOperacion = new DetalleOperacion(itemTest, 20F, 4);
	private List<DetalleOperacion> unosDetallesOperacion = Arrays.asList(unDetalleOperacion, unDetalleOperacion);
	private List<DetalleOperacion> otrosDetallesOperacion = Arrays.asList(unDetalleOperacion, otroDetalleOperacion);
	
	//OperacionEfectuada
	private LocalDate fechaOperacion;
	private MedioPago medioPago;
	
	//DetallePrecio
	private DetallePrecio unDetallePrecio = new DetallePrecio(unDetalleOperacion, 10F);
	private DetallePrecio otroDetallePrecio = new DetallePrecio(otroDetalleOperacion, 12F);
	private List<DetallePrecio> unosDetallesPrecio = Arrays.asList(unDetallePrecio, unDetallePrecio);
	private List<DetallePrecio> otrosDetallesPrecio = Arrays.asList(unDetallePrecio, otroDetallePrecio);
	//private List<DetallePrecio> variosDetallesPrecio = Arrays.asList(unDetallePrecio, unDetallePrecio, otroDetallePrecio, otroDetallePrecio);
	
	//Presupuesto
	private Presupuesto unPresupuesto = new Presupuesto(documentoComercialTest, entidadRealizadora, unosDetallesPrecio);
	private Presupuesto otroPresupuesto = new Presupuesto(documentoComercialTest, entidadRealizadora, otrosDetallesPrecio);
	private List<Presupuesto> listaPresupuestosVacia;
	private List<Presupuesto> listaPresupuestos = Arrays.asList(unPresupuesto, otroPresupuesto);
	private List<Presupuesto> otraListaPresupuestos = Arrays.asList(otroPresupuesto, otroPresupuesto);
	private List<Presupuesto> listaVariosPresupuestos = Arrays.asList(unPresupuesto, unPresupuesto, otroPresupuesto, otroPresupuesto);

	//CriterioCompra
	private CriterioCompra unCriterioCompra;
	private CriterioMenorPrecio criterioMenorPrecio = new CriterioMenorPrecio();

	//Egreso
	private Proveedor proveedorTest;
	private Usuario unRevisor = new Usuario("unRevisor", "asndihg382");
	private Usuario otroRevisor = new Usuario("otroRevisor", "wuiefnwi471");
	private List<Usuario> revisoresTest = Arrays.asList(unRevisor, otroRevisor);
	
	private Egreso egresoSinPresupuestosMinimos = new Egreso(documentoComercialTest, entidadRealizadora,
													unosDetallesOperacion, fechaOperacion, medioPago,
													0, unCriterioCompra, listaPresupuestosVacia, proveedorTest, revisoresTest);
	
	private Egreso egresoSinPresupuestos = new Egreso(documentoComercialTest, entidadRealizadora,
											unosDetallesOperacion, fechaOperacion, medioPago,
											2, unCriterioCompra, listaPresupuestosVacia, proveedorTest, revisoresTest);
	
	private Egreso egresoConPresupuestosSuficientes = new Egreso(documentoComercialTest, entidadRealizadora,
														unosDetallesOperacion, fechaOperacion, medioPago,
														2, unCriterioCompra, listaPresupuestos, proveedorTest, revisoresTest);
	
	private Egreso egresoConDetallesDeDistintoTamanio = new Egreso(documentoComercialTest, entidadRealizadora,
														unosDetallesOperacion, fechaOperacion, medioPago,
														2, unCriterioCompra, listaVariosPresupuestos, proveedorTest, revisoresTest);
	
	private Egreso egresoNoBasadoEnPresupuesto = new Egreso(documentoComercialTest, entidadRealizadora,
														otrosDetallesOperacion, fechaOperacion, medioPago,
														2, unCriterioCompra, otraListaPresupuestos, proveedorTest, revisoresTest);
	
	private Egreso egresoBasadoEnPresupuesto = new Egreso(documentoComercialTest, entidadRealizadora,
														unosDetallesOperacion, fechaOperacion, medioPago,
														2, unCriterioCompra, listaPresupuestos, proveedorTest, revisoresTest);
	
	//cumpleMinimoPresupuesto
	
	@Test
	public void cumpleMinimoPresupuestoSinPresupuestosMinimos() {
		assertTrue(validadorTest.validarCumpleMinimo(egresoSinPresupuestosMinimos));
	}
	
	@Test
	public void cumpleMinimoPresupuestoSinPresupuestos() {
		assertFalse(validadorTest.validarCumpleMinimo(egresoSinPresupuestos));
	}	

	@Test
	public void cumpleMinimoPresupuestoConSuficientesPresupuestos() {
		assertTrue(validadorTest.validarCumpleMinimo(egresoConPresupuestosSuficientes));
	}	
	
	//cumpleBasarseEnPresupuesto
	
	@Test
	public void cumpleBasarseEnPresupuestoConDetallesDeDistintoTamanio() {
		assertFalse(validadorTest.validarCumpleBasarse(egresoConDetallesDeDistintoTamanio));
	}
	
	@Test
	public void cumpleBasarseEnPresupuestoConEgresoNoBasado() {
		assertFalse(validadorTest.validarCumpleBasarse(egresoNoBasadoEnPresupuesto));
	}
	
	@Test
	public void cumpleBasarseEnPresupuestoConEgresoBasado() {
		assertTrue(validadorTest.validarCumpleBasarse(egresoBasadoEnPresupuesto));
	}
	
	//TODO:cumpleCriterio
	
	
    
    @Test
    public void notificarRevisoresEnvioOk() {
    	Validador validador = Validador.getInstance();
    	Usuario usuario = new Usuario("un usuario", "q1w2e3r4t5");
    	Usuario otroUsuario = new Usuario("un usuario", "q1w2e3r4t5");
    	List<Usuario> usuarios = Arrays.asList(usuario, otroUsuario);
    	Egreso egreso = new Egreso(10, 0, null, null, usuarios);
    	validador.notificarRevisores(egreso, true);
    	List<Mensaje> mensajeUsuario = usuario.getBandejaMensajes();
    	List<Mensaje> mensajeOtroUsuario = otroUsuario.getBandejaMensajes();
    	assertTrue("Validacion realizada con Exito".equals(mensajeUsuario.get(0).getCuerpo()) && 
    			"Validacion realizada con Exito".equals(mensajeOtroUsuario.get(0).getCuerpo()));
    }
    
    @Test
    public void notificarRevisoresEnvioError() {
    	Validador validador = Validador.getInstance();
    	Usuario usuario = new Usuario("un usuario", "q1w2e3r4t5");
    	Usuario otroUsuario = new Usuario("un usuario", "q1w2e3r4t5");
    	List<Usuario> usuarios = Arrays.asList(usuario, otroUsuario);
    	Egreso egreso = new Egreso(10, 0, null, null, usuarios);
    	validador.notificarRevisores(egreso, false);
    	List<Mensaje> mensajeUsuario = usuario.getBandejaMensajes();
    	List<Mensaje> mensajeOtroUsuario = otroUsuario.getBandejaMensajes();
    	assertTrue("Fallo de Validacion".equals(mensajeUsuario.get(0).getCuerpo()) && 
    			"Fallo de Validacion".equals(mensajeOtroUsuario.get(0).getCuerpo()));
    }
    
}

