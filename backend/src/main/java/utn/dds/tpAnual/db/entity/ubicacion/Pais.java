package utn.dds.tpAnual.db.entity.ubicacion;

import javax.persistence.*;

@Entity
@Table(name = "PAIS")
public class Pais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long paisId;

	@Column(name = "ID_API", unique = true, length = 100)
	private String idAPI;

	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;

	public Pais() {
	}

	public Pais(String descripcion, String idAPI) {
		this.descripcion = descripcion;
		this.idAPI = idAPI;
	}

    public Pais(String nombre) {
		this.descripcion = nombre;
    }

    public Long getPaisId() {
		return paisId;
	}

	public void setPaisId(Long paisId) {
		this.paisId = paisId;
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
