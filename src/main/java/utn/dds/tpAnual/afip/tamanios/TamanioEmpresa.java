package utn.dds.tpAnual.afip.tamanios;



/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public abstract class TamanioEmpresa {

	private int jerarquia;
	private String nombre;

	public TamanioEmpresa(){

	}

	public int getJerarquia() {
		return jerarquia;
	}

	public String getNombre() {
		return nombre;
	}
}