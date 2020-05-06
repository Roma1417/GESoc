package utn.dds.tpAnual.afip;

import java.util.Collections;
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

	public Sector(String nombreSector, List<RequisitoSectorEmpresa> requisitos) {
		super();
		this.nombreSector = nombreSector;
		this.requisitos = requisitos;
	}

	/**
	 * 
	 * @param emplados
	 * @param facturacion
	 */
	public TamanioEmpresa getTamanioPara(int empleados, Float facturacion){
		for(RequisitoSectorEmpresa requisito : getRequisitosOrdenados()){
			if(!requisito.excedeAlgunRequisito(empleados, facturacion)){
				return requisito.getTamanioEmpresa();
			}
		}
		throw new RuntimeException("No hay ninguna categoria posible");
	}
	
	private List<RequisitoSectorEmpresa> getRequisitosOrdenados(){
		Collections.sort(requisitos);
		return requisitos;
	}
}