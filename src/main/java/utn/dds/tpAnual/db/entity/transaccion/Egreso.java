package utn.dds.tpAnual.db.entity.transaccion;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.usuario.*;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCompra.CriterioCompra;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

import javax.persistence.*;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */

@Entity
@Table(name = "EGRESO")
public class Egreso extends OperacionEfectuada {

	@Column(name = "CANTIDAD_PRESUPUESTOS_MINIMOS")
	private int cantidadPresupuestosMinimos;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private CriterioCompra criterioCompra;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "EGRESO_ID")
	private List<Presupuesto> presupuestos;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Proveedor proveedor;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "EGRESO_ID")
	private Set<Usuario> revisores;

	@Column(name = "RESULTADO_VALIDACION")
	private String resultadoValidacion;

	@Column(name = "ES_VALIDACION_CORRECTA")
	private Boolean esValidacionCorrecta;

	public Egreso(DocumentoComercial documentoComercial, Entidad entidadRealizadora, int codigoOperacion,
			List<DetalleOperacion> detallesOperacion, LocalDate fechaOperacion, MedioPago medioPago,
			int cantidadPresupuestosMinimos, CriterioCompra criterioCompra, List<Presupuesto> presupuestos,
			Proveedor proveedor, Set<Usuario> revisores) {
		super(documentoComercial, entidadRealizadora, codigoOperacion, detallesOperacion, fechaOperacion, medioPago);
		this.cantidadPresupuestosMinimos = cantidadPresupuestosMinimos;
		this.criterioCompra = criterioCompra;
		this.presupuestos = presupuestos;
		this.proveedor = proveedor;
		this.revisores = revisores;
	}

	public Egreso() {
	}

	public Boolean getEsValidacionCorrecta() {
		return esValidacionCorrecta;
	}

	public void setEsValidacionCorrecta(Boolean esValidacionCorrecta) {
		this.esValidacionCorrecta = esValidacionCorrecta;
	}

	public void setCantidadPresupuestosMinimos(int cantidadPresupuestosMinimos) {
		this.cantidadPresupuestosMinimos = cantidadPresupuestosMinimos;
	}

	public void setCriterioCompra(CriterioCompra criterioCompra) {
		this.criterioCompra = criterioCompra;
	}

	public void setPresupuestos(List<Presupuesto> presupuestos) {
		this.presupuestos = presupuestos;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public void setRevisores(Set<Usuario> revisores) {
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
	
	public Set<Usuario> getRevisores() {
		return revisores;
	}

	public void setResultadoValidacion(String resultadoValidacion) {
		this.resultadoValidacion = resultadoValidacion;
	}

	public String getResultadoValidacion() {
		return this.resultadoValidacion;
	}
}