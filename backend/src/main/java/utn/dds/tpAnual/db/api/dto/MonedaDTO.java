package utn.dds.tpAnual.db.api.dto;

import java.util.List;
import java.util.stream.Collectors;

import utn.dds.tpAnual.db.entity.ubicacion.Moneda;

public class MonedaDTO {
	
	private String id;
	private String description;
	private String symbol;
	private Integer decimal_places;
	
	public Moneda toMoneda(){
		return new Moneda(id, description, symbol);
	}
	
	public static List<Moneda> toMonedas(List<MonedaDTO> monedaDTOList){
		return monedaDTOList.stream().map(monedaDTO -> monedaDTO.toMoneda()).collect(Collectors.toList());
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Integer getDecimal_places() {
		return decimal_places;
	}
	public void setDecimal_places(Integer decimal_places) {
		this.decimal_places = decimal_places;
	}
	
}
