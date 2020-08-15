package utn.dds.tpAnual.db.entity.proveedor;

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

	@Column(name = "DIR_POSTAL", nullable = false)
	private int direccionPostal;

	public Proveedor() {

	}

	public Long getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(Long proveedorId) {
		this.proveedorId = proveedorId;
	}

	public int getDireccionPostal() {
		return direccionPostal;
	}

	public void setDireccionPostal(int direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

	public Proveedor(int direccionPostal) {
		this.direccionPostal = direccionPostal;
	}
}