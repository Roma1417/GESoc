package utn.dds.tpAnual.db.entity.ubicacion;

import javax.persistence.*;

@Entity
@Table(name = "CIUDAD")
public class Ciudad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long ciudadId;

	@Column(name = "ID_API", unique = true, nullable = false, length = 100)
	private String idAPI;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Estado estado;

	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;

	public Ciudad() {
	}

	public Ciudad(Estado estado, String descripcion, String idAPI) {
		super();
		this.estado = estado;
		this.descripcion = descripcion;
		this.idAPI = idAPI;
	}

	public Long getCiudadId() {
		return ciudadId;
	}

	public void setCiudadId(Long ciudadId) {
		this.ciudadId = ciudadId;
	}
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdAPI() {
		return idAPI;
	}

	public void setIdAPI(String idAPI) {
		this.idAPI = idAPI;
	}

}
