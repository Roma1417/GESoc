package utn.dds.tpAnual.db.entity.transaccion;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "DETALLE_OPERACION")
public class DetalleOperacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, length = 100)
	private Long idDetalleOperacion;

	@Column(name = "CANTIDAD", nullable = false)
	private int cantidad;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private Item item;

	@Column(name = "PRECIO", nullable = false)
	private Float precio;

	public DetalleOperacion(Item item, Float precio, int cantidad){
		this.item = item;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public DetalleOperacion(){

	}

	public Long getIdDetalleOperacion() {
		return idDetalleOperacion;
	}

	public void setIdDetalleOperacion(Long idDetalleOperacion) {
		this.idDetalleOperacion = idDetalleOperacion;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}
	
    public Float getPrecio() {
		return precio;
	}
    
    public Item getItem() {
		return item;
	}
    
    public String getDescripcionItem() {
    	return item.getDescripcion();
    }
    
	public Float getTotal() {
		return cantidad * precio;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("\nitem", item)
			    .append("\nprecio", precio)
			    .append("\ncantidad", cantidad)
			    .toString();
	}
	
}
