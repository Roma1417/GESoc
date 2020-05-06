package utn.dds.tpAnual.entidad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import utn.dds.tpAnual.afip.Actividad;
import utn.dds.tpAnual.afip.VentaAnual;
import utn.dds.tpAnual.afip.tamanios.TamanioEmpresa;
import utn.dds.tpAnual.compra.DetalleOperacion;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
public class EntidadJuridicaEmpresa extends EntidadJuridica {

	private Actividad actividad;
	private int cantidadPersonal;
	private List<VentaAnual> ventasAnuales;

	public EntidadJuridicaEmpresa(){

	}
	/**
	 * 
	 * @param anio
	 */
	
	public Float getPromedioVentasParaAnios(int anios){
		int anioActual = LocalDate.now().getYear();
		Float total = 0F;
		Float cant = 0F;
		if(ventasAnuales != null){
			for (VentaAnual ventaAnual : ventasAnuales) {
				if(ventaAnual.getAnio() >= anioActual - anios) {
					total += ventaAnual.getVentasTotales();
					cant ++;
				}
			}
		}
		return total/anios;
	}
	
	public void addVentaAnual(VentaAnual venta) {
		if(ventasAnuales == null) {
			ventasAnuales = new ArrayList<VentaAnual>();
		}
		ventasAnuales.add(venta);
	}
	
	public TamanioEmpresa getTamanioEmpresa(){
		return actividad.getTamanioEmpresaPara(cantidadPersonal, getPromedioVentasParaAnios(3));
	}
	
}