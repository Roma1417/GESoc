package utn.dds.tpAnual.db.entity.usuario;

import org.apache.commons.lang3.builder.ToStringBuilder;
import utn.dds.tpAnual.db.entity.EntityInterface;

import javax.persistence.*;


/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
@Entity
@Table(name = "MENSAJE")
public class Mensaje implements EntityInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long mensajeId;

	@Column(name = "ASUNTO")
	private String asunto;

	@Column(name = "CUERPO")
	private String cuerpo;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Usuario usuario;

	public Mensaje(String asunto, String cuerpo){
		this.asunto = asunto;
		this.cuerpo = cuerpo;
	}

	public Mensaje() {
	}

	public String getAsunto() {
		return asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public Long getMensajeId() {
		return mensajeId;
	}

	public void setMensajeId(Long mensajeId) {
		this.mensajeId = mensajeId;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("resultado", cuerpo)
			    .toString();
	}

	@Override
	public Long getId() {
		return null;
	}
}