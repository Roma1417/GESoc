package utn.dds.tpAnual.afip;

import java.util.List;

import utn.dds.tpAnual.afip.tamanios.TamanioEmpresa;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class Sector {

	private String nombreSector;
	private List<RequisitoSectorEmpresa> requisitos;

	public Sector(){

	}

	/**
	 * 
	 * @param emplados
	 * @param facturacion
	 */
	public TamanioEmpresa getTamanioPara(int emplados, Float facturacion){
		for(RequisitoSectorEmpresa requisito :requisitos){
			if(!requisito.excedeAlgunRequisito(emplados,facturacion)){
				return requisito.getTamanioEmpresa();
			}
		}
		throw new RuntimeException("No hay ninguna categoria posible");


	}
}