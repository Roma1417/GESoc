package utn.dds.tpAnual.ubicacion;

public class Pais {

	private String descripcion;
	private String idAPI;

	public Pais() {
	}

	public Pais(String descripcion, String idAPI) {
		this.descripcion = descripcion;
		this.idAPI = idAPI;
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
