package utn.dds.tpAnual.db.api.dto;

import utn.dds.tpAnual.ubicacion.Ciudad;

public class CiudadDTO {

	private String id;
	private String name;
	private EstadoDTO state;
	private PaisDTO pais;
	private GeoInfoDTO geo_information;

	public CiudadDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EstadoDTO getState() {
		return state;
	}

	public void setState(EstadoDTO state) {
		this.state = state;
	}

	public PaisDTO getPais() {
		return pais;
	}

	public void setPais(PaisDTO pais) {
		this.pais = pais;
	}

	public GeoInfoDTO getGeo_information() {
		return geo_information;
	}

	public void setGeo_information(GeoInfoDTO geo_information) {
		this.geo_information = geo_information;
	}

	public Ciudad toCiudad() {
		return new Ciudad(state.toEstado(), name, id);
	}

}
