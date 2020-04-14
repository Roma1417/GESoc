package utn.dds.tpAnual.afip;



/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:20
 */
public class VentaAnual {

	private int anio;
	private Float ventasTotales;

	public VentaAnual(int anio, Float ventasTotales){
		this.anio = anio;
		this.ventasTotales = ventasTotales;
	}

	public VentaAnual(){

	}

	public Float getVentasTotales() {
		return ventasTotales;
	}
	
	public int getAnio() {
		return anio;
	}
}