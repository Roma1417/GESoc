package utn.dds.tpAnual.db.entity.usuario;

import utn.dds.tpAnual.db.entity.entidad.Entidad;

import javax.persistence.*;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
@Entity
@Table(name = "USUARIO_ENTIDAD")
public class UsuarioEntidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long usuarioEntidadId;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Entidad entidad;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private TipoUsuario tipoUsuario;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Usuario usuario;

	public UsuarioEntidad(){

	}

	public UsuarioEntidad(Entidad entidad, TipoUsuario tipoUsuario, Usuario usuario) {
		this.entidad = entidad;
		this.tipoUsuario = tipoUsuario;
		this.usuario = usuario;
	}

	public Long getUsuarioEntidadId() {
		return usuarioEntidadId;
	}

	public void setUsuarioEntidadId(Long usuarioEntidadId) {
		this.usuarioEntidadId = usuarioEntidadId;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean puedeVerMensajesDeOtros() {
		return this.tipoUsuario.puedeVerMensajesDeOtros();
	}
}