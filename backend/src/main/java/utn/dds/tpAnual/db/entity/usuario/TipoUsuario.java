package utn.dds.tpAnual.db.entity.usuario;


import utn.dds.tpAnual.db.entity.EntityInterface;

import javax.persistence.*;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "TIPO_USUARIO")
public abstract class TipoUsuario implements EntityInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private Long tipoUsuarioId;

	@Column(name = "descripcion")
	private String descripcion;

	public TipoUsuario(){

	}

	public Long getTipoUsuarioId() {
		return tipoUsuarioId;
	}

	public void setTipoUsuarioId(Long tipoUsuarioId) {
		this.tipoUsuarioId = tipoUsuarioId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public abstract boolean puedeVerMensajesDeOtros();
}