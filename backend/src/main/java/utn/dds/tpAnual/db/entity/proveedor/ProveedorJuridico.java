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
public class ProveedorJuridico extends Proveedor {

	@Column(name = "CUIT")
	private String CUIT;

	public ProveedorJuridico() {

	}

	public ProveedorJuridico(DireccionPostal direccionPostal, String CUIT, String razonSocial) {
		super(direccionPostal, razonSocial);
		this.CUIT = CUIT;

	}

	public String getCUIT() {
		return CUIT;
	}

	public void setCUIT(String CUIT) {
		this.CUIT = CUIT;
	}


	@Override
	public Long getId() {
		return null;
	}
}