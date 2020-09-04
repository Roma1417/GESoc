package utn.dds.tpAnual.ubicacion;

public class Ciudad {

	private Estado estado;
	private String descripcion;
	private String idAPI;

	public Ciudad() {
	}

	public Ciudad(Estado estado, String descripcion, String idAPI) {
		super();
		this.estado = estado;
		this.descripcion = descripcion;
		this.idAPI = idAPI;
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
