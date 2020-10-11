package utn.dds.tpAnual.db.entity.usuario;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
@Entity(name = "Estandar")
@DiscriminatorValue("Estandar")
public class Estandar extends TipoUsuario {

	private final String descripcion = "Usuario estándar";

	public Estandar(){

	}

	@Override
	public boolean puedeVerMensajesDeOtros() {
		return true;
	}

}