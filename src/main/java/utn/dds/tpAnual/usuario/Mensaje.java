package utn.dds.tpAnual.usuario;



/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class Mensaje {

	private String asunto;
	private String cuerpo;

	public Mensaje(String asunto, String cuerpo){
		this.asunto = asunto;
		this.cuerpo = cuerpo;
	}

	public String getAsunto() {
		return asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}
	
	

}