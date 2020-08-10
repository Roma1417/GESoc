package utn.dds.tpAnual.afip;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:20
 */

@Entity
@Table(name ="VENTA_ANUAL")
public class VentaAnual {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "ANIO", unique = true, nullable = false)
	private int anio;

	@Column(name = "VENTAS_TOTALES", nullable = false)
	private Float ventasTotales;

	public VentaAnual(int anio, Float ventasTotales){
		this.anio = anio;
		this.ventasTotales = ventasTotales;
	}

	public VentaAnual(){

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public void setVentasTotales(Float ventasTotales) {
		this.ventasTotales = ventasTotales;
	}

	public Float getVentasTotales() {
		return ventasTotales;
	}
	
	public int getAnio() {
		return anio;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}