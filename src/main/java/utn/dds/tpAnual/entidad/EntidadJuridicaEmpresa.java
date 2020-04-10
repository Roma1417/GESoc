package utn.dds.tpAnual.entidad;

import java.util.List;

import utn.dds.tpAnual.afip.Actividad;
import utn.dds.tpAnual.afip.TamanioEmpresa;
import utn.dds.tpAnual.afip.VentaAnual;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
public class EntidadJuridicaEmpresa extends EntidadJuridica {

	private Actividad actividad;
	private int cantidadPersonal;
	private List<VentaAnual> ventasAnuales;
	public VentaAnual m_VentaAnual;

	public EntidadJuridicaEmpresa(){

	}
	/**
	 * 
	 * @param anio
	 */
	private Float getPromedioVentasParaAnio(int anio){
		return null;
	}

	public TamanioEmpresa getTamanioEmpresa(){
		return null;
	}
}