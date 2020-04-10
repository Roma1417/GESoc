

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 17:39:45
 */
public class EntidadBase extends Entidad {

	private String descripcion;
	private EntidadJuridica entidadJuridica;

	public EntidadBase(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
}//end EntidadBase