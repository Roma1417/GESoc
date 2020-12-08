package utn.dds.tpAnual.db.entity.ubicacion;

import utn.dds.tpAnual.db.entity.EntityInterface;

import javax.persistence.*;

@Entity
@Table(name = "ESTADO")
public class Estado  implements EntityInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long estadoId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Pais pais;

	@Column(name = "ID_API", unique = true, nullable = false, length = 100)
	private String idAPI;

	@Column(name = "DESCRIPCION", unique = false, nullable = false, length = 100)
	private String descripcion;

	public Estado() {
	}

	public Estado(Pais pais, String descripcion, String idAPI) {
		this.pais = pais;
		this.descripcion = descripcion;
		this.idAPI = idAPI;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
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

	public Long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}
}
