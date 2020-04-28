package utn.dds.tpAnual.compra;

import java.time.LocalDate;
import java.util.List;

import utn.dds.tpAnual.proveedor.Proveedor;
import utn.dds.tpAnual.usuario.Usuario;
import utn.dds.tpAnual.validador.CriterioCompra;
import utn.dds.tpAnual.entidad.Entidad;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
public class Egreso extends OperacionEfectuada {

	private int cantidadPresupuestosMinimos;
	private CriterioCompra criterioCompra;
	private List<Presupuesto> presupuestos;
	private Proveedor proveedor;
	private List<Usuario> revisores;
	
	public Egreso(DocumentoComercial documentoComercial, Entidad entidadRealizadora, List<DetalleOperacion> detallesOperacion,
			LocalDate fechaOperacion, MedioPago medioPago,
			int cantidadPresupuestosMinimos, CriterioCompra criterioCompra, List<Presupuesto> presupuestos,
			Proveedor proveedor, List<Usuario> revisores) {
		super(documentoComercial, entidadRealizadora, detallesOperacion, fechaOperacion, medioPago);
		this.cantidadPresupuestosMinimos = cantidadPresupuestosMinimos;
		this.criterioCompra = criterioCompra;
		this.presupuestos = presupuestos;
		this.proveedor = proveedor;
		this.revisores = revisores;
	}
	
	public int getCantidadPresupuestosMinimos() {
		return cantidadPresupuestosMinimos;
	}
	
	public CriterioCompra getCriterioCompra() {
		return criterioCompra;
	}
	
	public List<Presupuesto> getPresupuestos(){
		return presupuestos;
	}
}