package utn.dds.tpAnual.db.entity.transaccion;

import org.apache.commons.lang3.builder.ToStringBuilder;
import utn.dds.tpAnual.db.entity.EntityInterface;

import javax.persistence.*;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */

@Entity
@Table(name = "DETALLE_PRECIO")
public class DetallePrecio implements EntityInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long detallePrecioId;

	@OneToOne(cascade = CascadeType.ALL)
	private DetalleOperacion detalleOperacion;

	@Column(name = "PRECIO", nullable = false)
	private Float precio;

	public DetallePrecio(DetalleOperacion detalleOperacion, Float precio){
		this.detalleOperacion = detalleOperacion;
		this.precio = precio;
	}

	public DetallePrecio(){

	}

	public void setDetalleOperacion(DetalleOperacion detalleOperacion) {
		this.detalleOperacion = detalleOperacion;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Float getPrecio() {
		return precio;
	}
	
	public DetalleOperacion getDetalleOperacion() {
		return detalleOperacion;
	}
	
	public Float getPrecioTotal() {
		return precio * detalleOperacion.getCantidad();
	}

	public Long getDetallePrecioId() {
		return detallePrecioId;
	}

	public void setDetallePrecioId(Long detallePrecioId) {
		this.detallePrecioId = detallePrecioId;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("\ndetalleOperacion", detalleOperacion)
			    .append("\nprecio", precio)
			    .toString();
	}

	@Override
	public Long getId() {
		return null;
	}
}
