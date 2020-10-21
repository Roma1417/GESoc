package utn.dds.tpAnual.db.entity.proveedor;

import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;

import javax.persistence.*;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "PROVEEDOR")
public abstract class Proveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long proveedorId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private DireccionPostal direccionPostal;

	@Column(name = "NOMBRE_RAZON_SOCIAL", length = 100)
	private String nombreRazonSocial;

	public Proveedor() {

	}

	public DireccionPostal getDireccionPostal() {
		return direccionPostal;
	}

	public void setDireccionPostal(DireccionPostal direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

	public Long getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(Long proveedorId) {
		this.proveedorId = proveedorId;
	}

	public Proveedor(DireccionPostal direccionPostal, String nombreRazonSocial) {
		this.direccionPostal = direccionPostal;
		this.nombreRazonSocial = nombreRazonSocial;
	}

	public String getNombreRazonSocial() {
		return nombreRazonSocial;
	}

	public void setNombreRazonSocial(String nombreRazonSocial) {
		this.nombreRazonSocial = nombreRazonSocial;
	}
}