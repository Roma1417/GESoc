package utn.dds.tpAnual.usuario;

import org.apache.commons.lang3.builder.ToStringBuilder;

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
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("resultado", cuerpo)
			    .toString();
	}

}