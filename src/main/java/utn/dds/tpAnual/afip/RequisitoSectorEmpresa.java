package utn.dds.tpAnual.afip;

import utn.dds.tpAnual.afip.tamanios.TamanioEmpresa;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class RequisitoSectorEmpresa implements Comparable<RequisitoSectorEmpresa>{

	private int maximoEmpleados;
	private Float maximoFacturacion;
	private TamanioEmpresa tamanioEmpresa;
	
	public RequisitoSectorEmpresa(int maximoEmpleados, Float maximoFacturacion, TamanioEmpresa tamanioEmpresa) {
		this.maximoEmpleados = maximoEmpleados;
		this.maximoFacturacion = maximoFacturacion;
		this.tamanioEmpresa = tamanioEmpresa;
	}

	public int getMaximoEmpleados() {
		return maximoEmpleados;
	}

	public Float getMaximoFacturacion() {
		return maximoFacturacion;
	}

	/**
	 * 
	 * @param cantidadEmpleados
	 * @param facturacionMaxima
	 */
	public boolean excedeAlgunRequisito(int cantidadEmpleados, Float facturacionMaxima){
		return cantidadEmpleados > this.maximoEmpleados 
				|| facturacionMaxima > this.maximoFacturacion;
	}

	public TamanioEmpresa getTamanioEmpresa() {
		return tamanioEmpresa;
	}

	public void setTamanioEmpresa(TamanioEmpresa tamanioEmpresa) {
		this.tamanioEmpresa = tamanioEmpresa;
	}

	public int compareTo(RequisitoSectorEmpresa requisitoSectorEmpresa) {       
		int jerarquia = this.tamanioEmpresa.getJerarquia();
		int otraJerarquia = requisitoSectorEmpresa.getTamanioEmpresa().getJerarquia();
		return jerarquia == otraJerarquia ? 0 : 
			jerarquia > otraJerarquia ? 1 : -1;
	}    

}