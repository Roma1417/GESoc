

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 17:39:45
 */
public abstract class EntidadJuridica extends Entidad {

	private Long codigoIGJ;
	private Long CUIT;
	private int direccionPostal;
	private List<EntidadBase> entidadesBase;
	private String razonSocial;
	public EntidadBase m_EntidadBase;

	public EntidadJuridica(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
}//end EntidadJuridica