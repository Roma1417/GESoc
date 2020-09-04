package utn.dds.tpAnual.ubicacion;

public class Estado {

	private Pais pais;
	private String descripcion;
	private String idAPI;

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
}
