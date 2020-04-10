package utn.dds.tpAnual.afip;

import utn.dds.tpAnual.entidad.EntidadJuridicaEmpresa;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
public class Actividad {

	private String nombreActividad;
	private Sector sector;
	public EntidadJuridicaEmpresa entidadJuridicaEmpresa;

	public Actividad(){

	}

	/**
	 * 
	 * @param cantidadEmpleados
	 * @param ventasAnuales
	 */
	public TamanioEmpresa getTamanioEmpresaPara(int cantidadEmpleados, Float ventasAnuales){
		return this.sector.getTamanioPara(cantidadEmpleados, ventasAnuales);
	}
}