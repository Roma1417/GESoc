package utn.dds.tpAnual.afip;

import utn.dds.tpAnual.afip.tamanios.TamanioEmpresa;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class RequisitoSectorEmpresa {

	private int maximoEmpleados;
	private Float maximoFacturacion;
	private TamanioEmpresa tamanioEmpresa;

	public RequisitoSectorEmpresa(){

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
	
	
}