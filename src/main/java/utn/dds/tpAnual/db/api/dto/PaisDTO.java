package utn.dds.tpAnual.db.api.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import utn.dds.tpAnual.ubicacion.Pais;

public class PaisDTO {

	private String id;
	private String name;
	private String locale;
	private String currency_id;
	private String decimal_separator;
	private String thousands_separator;
	private String time_zone;
	private GeoInfoDTO geo_information;
	private List<EstadoDTO> states;

	public Pais toPais(){
		return new Pais(name, id);
	}

	public static List<Pais> toPaises(List<PaisDTO> paisDTOList){
		return paisDTOList.stream().map(paisDTO -> paisDTO.toPais()).collect(Collectors.toList());
	}

	public PaisDTO() {
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

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getCurrency_id() {
		return currency_id;
	}

	public void setCurrency_id(String currency_id) {
		this.currency_id = currency_id;
	}

	public String getDecimal_separator() {
		return decimal_separator;
	}

	public void setDecimal_separator(String decimal_separator) {
		this.decimal_separator = decimal_separator;
	}

	public String getThousands_separator() {
		return thousands_separator;
	}

	public void setThousands_separator(String thousands_separator) {
		this.thousands_separator = thousands_separator;
	}

	public String getTime_zone() {
		return time_zone;
	}

	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}

	public GeoInfoDTO getGeo_information() {
		return geo_information;
	}

	public void setGeo_information(GeoInfoDTO geo_information) {
		this.geo_information = geo_information;
	}

	public List<EstadoDTO> getStates() {
		return states;
	}

	public void setStates(List<EstadoDTO> states) {
		this.states = states;
	}
}
