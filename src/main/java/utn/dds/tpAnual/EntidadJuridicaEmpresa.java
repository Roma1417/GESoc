

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 17:39:45
 */
public class EntidadJuridicaEmpresa extends EntidadJuridica {

	private Actividad actividad;
	private int cantidadPersonal;
	private List<VentaAnual> ventasAnuales;
	public VentaAnual m_VentaAnual;

	public EntidadJuridicaEmpresa(){

	}

	public void finalize() throws Throwable {
		super.finalize();
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
}//end EntidadJuridicaEmpresa