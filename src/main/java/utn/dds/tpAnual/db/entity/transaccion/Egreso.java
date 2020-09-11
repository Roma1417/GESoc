package utn.dds.tpAnual.db.entity.transaccion;

import java.time.LocalDate;
import java.util.List;

import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.usuario.Usuario;
import utn.dds.tpAnual.validador.CriterioCompra;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

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
	private List<Ingreso> ingresosAsociados;
	
	public Egreso(DocumentoComercial documentoComercial, Entidad entidadRealizadora, int codigoOperacion,
			List<DetalleOperacion> detallesOperacion, LocalDate fechaOperacion, MedioPago medioPago,
			int cantidadPresupuestosMinimos, CriterioCompra criterioCompra, List<Presupuesto> presupuestos,
			Proveedor proveedor, List<Usuario> revisores) {
		super(documentoComercial, entidadRealizadora, codigoOperacion, detallesOperacion, fechaOperacion, medioPago);
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
	
	public List<Usuario> getRevisores() {
		return revisores;
	}

	public List<Ingreso> getIngresosAsociados() {
		return ingresosAsociados;
	}

	public void setIngresosAsociados(List<Ingreso> ingresosAsociados) {
		this.ingresosAsociados = ingresosAsociados;
	}
}