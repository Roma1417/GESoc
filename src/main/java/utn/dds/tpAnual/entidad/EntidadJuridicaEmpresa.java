package utn.dds.tpAnual.entidad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

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
	private TamanioEmpresa tamanioEmpresa;

	public EntidadJuridicaEmpresa(){

	}
	
	
	
	public EntidadJuridicaEmpresa(Actividad actividad, int cantidadPersonal, List<VentaAnual> ventasAnuales, String nombre) {
		super(nombre);
		this.actividad = actividad;
		this.cantidadPersonal = cantidadPersonal;
		this.ventasAnuales = ventasAnuales;
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
		return this.tamanioEmpresa;
	}
	
	public void recalcularTamanioEmpresa(){
		this.tamanioEmpresa = actividad.getTamanioEmpresaPara(cantidadPersonal, getPromedioVentasParaAnios(3));
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("nombre", getNombre())
				.append("actividad", actividad.toString())
				.append("cantidad personal", String.valueOf(cantidadPersonal))
				.append("ventas anuales", ventasAnuales.toString())
				.append("tamaño empresa", tamanioEmpresa.getNombre())
			    .toString();
	}
	
}