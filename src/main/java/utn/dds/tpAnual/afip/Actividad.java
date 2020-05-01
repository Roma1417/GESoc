package utn.dds.tpAnual.afip;

import utn.dds.tpAnual.afip.tamanios.TamanioEmpresa;
import utn.dds.tpAnual.entidad.EntidadJuridicaEmpresa;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
public class Actividad {

	private String nombreActividad;
	private Sector sector;

	public Actividad(String nombreActividad, Sector sector) {
		super();
		this.nombreActividad = nombreActividad;
		this.sector = sector;
	}
	/**
	 * 
	 * @param cantidadEmpleados
	 * @param ventasAnuales
	 */
	public TamanioEmpresa getTamanioEmpresaPara(int cantidadEmpleados, Float ventasAnuales){
		return sector.getTamanioPara(cantidadEmpleados, ventasAnuales);
	}
}