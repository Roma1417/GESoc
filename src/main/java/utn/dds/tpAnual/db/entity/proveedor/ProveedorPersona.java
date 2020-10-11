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
	private String dni;

	public ProveedorPersona() {

	}

	public ProveedorPersona(DireccionPostal direccionPostal, String dni, String nombre) {
		super(direccionPostal, nombre);
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}


}