package utn.dds.tpAnual.validador;

import static org.junit.Assert.*;

import java.time.LocalDate;
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
	private Validador validadorTest = Validador.getInstance();
	
	//Operacion
	private DocumentoComercial documentoComercialTest;
	private Entidad entidadRealizadora;
	private int codigoOperacion;
	
	//DetalleOperacion
	private Item itemTest = new Item(123L, "itemTest");
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
	private Presupuesto unPresupuesto = new Presupuesto(documentoComercialTest, entidadRealizadora, codigoOperacion, unosDetallesPrecio);
	private Presupuesto otroPresupuesto = new Presupuesto(documentoComercialTest, entidadRealizadora, codigoOperacion, otrosDetallesPrecio);
	private List<Presupuesto> listaPresupuestosVacia;
	private List<Presupuesto> listaPresupuestos = Arrays.asList(unPresupuesto, otroPresupuesto);
	private List<Presupuesto> unaListaPresupuestos = Arrays.asList(unPresupuesto, unPresupuesto);
	private List<Presupuesto> otraListaPresupuestos = Arrays.asList(otroPresupuesto, otroPresupuesto);
	private List<Presupuesto> listaVariosPresupuestos = Arrays.asList(unPresupuesto, unPresupuesto, otroPresupuesto, otroPresupuesto);

	//Criterio Compra
	private CriterioMenorPrecio criterioMenorPrecio = new CriterioMenorPrecio();
	
	//Egreso
	//private CriterioCompra unCriterioCompra;
	//private Proveedor proveedorTest;
	private Usuario unRevisor = new Usuario("unRevisor", "asndihg382");
	private Usuario otroRevisor = new Usuario("otroRevisor", "wuiefnwi471");
	private List<Usuario> revisoresTest = Arrays.asList(unRevisor, otroRevisor);
	
	private Egreso egresoSinPresupuestos = new Egreso(null, null, 542, null, null, null, 2, criterioMenorPrecio, listaPresupuestosVacia, null, null);
	
	private Egreso egresoRevisores = new Egreso(null, null, 0, null, null, null, 10, null, null, null, revisoresTest);
	
	
//	private Egreso egresoTest = new Egreso(documentoComercialTest, entidadRealizadora, codigoOperacion,
//										unosDetallesOperacion, fechaOperacion, medioPago,
//										cantPresupMinimos, unCriterioCompra, listaPresupuestosVacia, proveedorTest, revisoresTest);
	
	//cumpleMinimoPresupuesto
	
	@Test
	public void cumpleMinimoPresupuestoSinPresupuestosMinimos() {
		Egreso egresoSinPresupuestosMinimos = new Egreso(null, null, 123, null, null, null, 0, null, null, null, null);
		assertTrue(validadorTest.testearCumpleMinimo(egresoSinPresupuestosMinimos));
	}
	
	@Test
	public void cumpleMinimoPresupuestoSinPresupuestos() {
		assertFalse(validadorTest.testearCumpleMinimo(egresoSinPresupuestos));
	}	

	@Test
	public void cumpleMinimoPresupuestoConSuficientesPresupuestos() {
		Egreso egresoConPresupuestosSuficientes = new Egreso(null, null, 342, null, null, null, 2, null, listaPresupuestos, null, null);
		assertTrue(validadorTest.testearCumpleMinimo(egresoConPresupuestosSuficientes));
	}
	
	//cumpleBasarseEnPresupuesto
	
	@Test
	public void cumpleBasarseEnPresupuestoConDetallesDeDistintoTamanio() {
		Egreso egresoConDetallesDeDistintoTamanio = new Egreso(null, null, 475, unosDetallesOperacion, null, null, 2, null, listaVariosPresupuestos, null, null);
		assertFalse(validadorTest.testearCumpleBasarse(egresoConDetallesDeDistintoTamanio));
	}
	
	@Test
	public void cumpleBasarseEnPresupuestoConEgresoNoBasado() {
		Egreso egresoNoBasadoEnPresupuesto = new Egreso(null, null, 345, otrosDetallesOperacion, null, null, 2, null, otraListaPresupuestos, null, null);
		assertFalse(validadorTest.testearCumpleBasarse(egresoNoBasadoEnPresupuesto));
	}
	
	@Test
	public void cumpleBasarseEnPresupuestoConEgresoBasado() {
		Egreso egresoBasadoEnPresupuesto = new Egreso(null, null, 493, unosDetallesOperacion, null, null, 2, null, listaPresupuestos, null, null);
		assertTrue(validadorTest.testearCumpleBasarse(egresoBasadoEnPresupuesto));
	}
	
	//TODO:cumpleCriterio
	
	@Test 
	public void cumpleCriterioSinPresupuestoCumplidor() {
		assertTrue(validadorTest.testearCumpleCriterio(egresoSinPresupuestos));
	}
	
	public void cumpleCriterioSinCoincidenciaDePrecios() {
		Egreso egresoIncumplidor = new Egreso(null, null, 87, unosDetallesOperacion, null, null, 2, criterioMenorPrecio, otraListaPresupuestos, null, null);
		assertFalse(validadorTest.testearCumpleCriterio(egresoIncumplidor));
	}
	
	public void cumpleCriterioMenorPrecio() {
		Egreso egresoCumplidorDeCriterio = new Egreso(null, null, 87, unosDetallesOperacion, null, null, 2, criterioMenorPrecio, unaListaPresupuestos, null, null);
		assertTrue(validadorTest.testearCumpleCriterio(egresoCumplidorDeCriterio));
	}
	
	//notificarRevisores
    
    @Test
    public void notificarRevisoresEnvioOk() {
    	validadorTest.testearNotificar(egresoRevisores, true);
    	List<Mensaje> mensajeUsuario = unRevisor.getBandejaMensajes();
    	List<Mensaje> mensajeOtroUsuario = otroRevisor.getBandejaMensajes();
    	
    	assertTrue("Validacion realizada con Exito".equals(mensajeUsuario.get(0).getCuerpo()) && 
    			"Validacion realizada con Exito".equals(mensajeOtroUsuario.get(0).getCuerpo()));
    }
    
    @Test
    public void notificarRevisoresEnvioError() {
    	validadorTest.testearNotificar(egresoRevisores, false);
    	List<Mensaje> mensajeUsuario = unRevisor.getBandejaMensajes();
    	List<Mensaje> mensajeOtroUsuario = otroRevisor.getBandejaMensajes();
    	
    	assertTrue("Fallo de Validacion".equals(mensajeUsuario.get(0).getCuerpo()) && 
    			"Fallo de Validacion".equals(mensajeOtroUsuario.get(0).getCuerpo()));
    }
    
}

