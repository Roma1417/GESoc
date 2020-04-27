package utn.dds.tpAnual.validador;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import utn.dds.tpAnual.compra.*;
import utn.dds.tpAnual.entidad.Entidad;
import utn.dds.tpAnual.proveedor.Proveedor;
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
	private List<DetalleOperacion> unosDetallesOperacion = Arrays.asList(unDetalleOperacion, otroDetalleOperacion);
	
	//OperacionEfectuada
	private LocalDate fechaOperacion;
	private MedioPago medioPago;
	private DetalleOperacion m_DetalleOperacion;
	
	//DetallePrecio
	private DetallePrecio unDetallePrecio = new DetallePrecio(unDetalleOperacion, 10F);
	private DetallePrecio otroDetallePrecio = new DetallePrecio(otroDetalleOperacion, 12F);
	private List<DetallePrecio> unosDetallesPrecio = Arrays.asList(unDetallePrecio, otroDetallePrecio);
	private List<DetallePrecio> otrosDetallesPrecio = Arrays.asList(unDetallePrecio, unDetallePrecio, otroDetallePrecio, otroDetallePrecio);
	
	//Presupuesto
	private Presupuesto unPresupuesto = new Presupuesto(documentoComercialTest, entidadRealizadora, unosDetallesPrecio);
	private Presupuesto otroPresupuesto = new Presupuesto(documentoComercialTest, entidadRealizadora, otrosDetallesPrecio);
	private List<Presupuesto> listaPresupuestos = Arrays.asList(unPresupuesto, otroPresupuesto);
	private List<Presupuesto> listaPresupuestosVacia;

	//CriterioCompra
	private CriterioCompra unCriterioCompra;
	private CriterioMenorPrecio criterioMenorPrecio = new CriterioMenorPrecio();

	//Egreso
	private Proveedor proveedorTest;
	private Usuario unRevisor = new Usuario("unRevisor", "asndihg382");
	private Usuario otroRevisor = new Usuario("otroRevisor", "wuiefnwi471");
	private List<Usuario> revisoresTest = Arrays.asList(unRevisor, otroRevisor);
	
	Egreso egresoSinPresupuestosMinimos = new Egreso(documentoComercialTest, entidadRealizadora,
													unosDetallesOperacion, fechaOperacion, medioPago, m_DetalleOperacion,
													0, unCriterioCompra, listaPresupuestosVacia, proveedorTest, revisoresTest);
	Egreso egresoSinPresupuestos = new Egreso(documentoComercialTest, entidadRealizadora,
											unosDetallesOperacion, fechaOperacion, medioPago, m_DetalleOperacion,
											2, unCriterioCompra, listaPresupuestosVacia, proveedorTest, revisoresTest);
	Egreso egresoConPresupuestosSuficientes = new Egreso(documentoComercialTest, entidadRealizadora,
														unosDetallesOperacion, fechaOperacion, medioPago, m_DetalleOperacion,
														2, unCriterioCompra, listaPresupuestos, proveedorTest, revisoresTest);
		
	//----TESTS----
	
	//cumpleMinimoPresupuesto
	
	@Test
	public void cumpleMinimoPresupuestoSinPresupuestosMinimos() {
		assertTrue(validadorTest.cumpleMinimoPresupuesto(egresoSinPresupuestosMinimos));
	}
	
	@Test
	public void cumpleMinimoPresupuestoSinPresupuestos() {
		assertFalse(validadorTest.cumpleMinimoPresupuesto(egresoSinPresupuestos));
	}	

	@Test
	public void cumpleMinimoPresupuestoConSuficientesPresupuestos() {
		assertTrue(validadorTest.cumpleMinimoPresupuesto(egresoConPresupuestosSuficientes));
	}	
	
	//cumpleBasarseEnPresupuesto
	
	@Test
	public void cumpleBasarseEnPresupuestoSinPresupuestos() {
		assertFalse(validadorTest.cumpleBasarseEnPresupuesto(egresoSinPresupuestos));
	}
	
	//cumpleCriterio
	

}

