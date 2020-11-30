package utn.dds.tpAnual.db.entity.usuario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
@Entity(name = "Admin")
@DiscriminatorValue("Admin")
public class Admin extends TipoUsuario {

	private final String descripcion = "Usuario administrador";

	public Admin(){

	}

	@Override
	public boolean puedeVerMensajesDeOtros() {
		return true;
	}

}