package utn.dds.tpAnual.db.entity.transaccion;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "DETALLE_PRECIO")
public class DetallePrecio {

	@OneToOne(cascade = CascadeType.REFRESH)
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
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("\ndetalleOperacion", detalleOperacion)
			    .append("\nprecio", precio)
			    .toString();
	}
}
