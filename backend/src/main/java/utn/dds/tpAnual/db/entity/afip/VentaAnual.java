package utn.dds.tpAnual.db.entity.afip;

import org.apache.commons.lang3.builder.ToStringBuilder;
import utn.dds.tpAnual.db.entity.EntityInterface;

import javax.persistence.*;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:20
 */

@Entity
@Table(name ="VENTA_ANUAL")
public class VentaAnual implements EntityInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long ventaAnualId;

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

	public Long getVentaAnualId() {
		return ventaAnualId;
	}

	public void setVentaAnualId(Long ventaAnualId) {
		this.ventaAnualId = ventaAnualId;
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

	@Override
	public Long getId() {
		return getVentaAnualId();
	}
}