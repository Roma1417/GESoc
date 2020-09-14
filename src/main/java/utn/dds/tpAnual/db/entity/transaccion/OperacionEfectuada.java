package utn.dds.tpAnual.db.entity.transaccion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import utn.dds.tpAnual.db.entity.entidad.Entidad;

import javax.persistence.*;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */

@Entity
@Table(name = "OPERACION_EFECTUADA")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class OperacionEfectuada extends Operacion {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DetalleOperacion> detallesOperacion;

	@Column(name = "FECHA_OPERACION_EFECTUADA")
	private LocalDate fechaOperacion;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private MedioPago medioPago;
	
	public OperacionEfectuada(DocumentoComercial documentoComercial, Entidad entidadRealizadora, int codigoOperacion,
			List<DetalleOperacion> detallesOperacion, LocalDate fechaOperacion, MedioPago medioPago) {
		super(documentoComercial, entidadRealizadora, codigoOperacion);
		this.detallesOperacion = detallesOperacion;
		this.fechaOperacion = fechaOperacion;
		this.medioPago = medioPago;
	}

	public OperacionEfectuada() {
	}

	public void setDetallesOperacion(List<DetalleOperacion> detallesOperacion) {
		this.detallesOperacion = detallesOperacion;
	}

	public LocalDate getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(LocalDate fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public MedioPago getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	public Float getTotal(){
		Float total = 0F;
		if(detallesOperacion!=null) {
			for (DetalleOperacion detalle : detallesOperacion) {
				total += detalle.getTotal();
			}
		}
		return total;
	}
	
	public List<DetalleOperacion> getDetallesOperacion() {
		return detallesOperacion;
	}
	
	public void addDetalleOperacion(DetalleOperacion detalle) {
		if(detallesOperacion == null) {
			detallesOperacion = new ArrayList<DetalleOperacion>();
		}
		detallesOperacion.add(detalle);
	}
}