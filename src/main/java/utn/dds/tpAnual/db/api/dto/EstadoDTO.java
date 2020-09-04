package utn.dds.tpAnual.db.api.dto;

import java.util.List;

import utn.dds.tpAnual.ubicacion.Estado;

public class EstadoDTO {

	private String id;
	private String name;
	private PaisDTO country; 
	private GeoInfoDTO geo_information;
	private List<CiudadDTO> cities;

	public EstadoDTO() {
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

	public PaisDTO getCountry() {
		return country;
	}

	public void setCountry(PaisDTO country) {
		this.country = country;
	}

	public GeoInfoDTO getGeo_information() {
		return geo_information;
	}

	public void setGeo_information(GeoInfoDTO geo_information) {
		this.geo_information = geo_information;
	}

	public List<CiudadDTO> getCities() {
		return cities;
	}

	public void setCities(List<CiudadDTO> cities) {
		this.cities = cities;
	}
	
	public Estado toEstado() {
		return new Estado(country.toPais(), name, id);
	}
}
