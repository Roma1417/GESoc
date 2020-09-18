package utn.dds.tpAnual.db.entity.proveedor;

import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */

@Entity
public class ProveedorPersona extends Proveedor {

	@Column(name = "DNI")
	private Long dni;

	@Column(name = "NOMBRE", length = 100)
	private String nombre;

	public ProveedorPersona() {

	}

	public ProveedorPersona(DireccionPostal direccionPostal, Long dni, String nombre) {
		super(direccionPostal);
		this.dni = dni;
		this.nombre = nombre;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}