package utn.dds.tpAnual.afip.tamanios;



/**
 * @author Rdiaz
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class Micro extends TamanioEmpresa{

	private final int jerarquia = 0;
	private final String nombre = "Micro";
	private static Micro instance = new Micro();
	
	public static Micro getInstance() {
		return instance;
	}
	
}