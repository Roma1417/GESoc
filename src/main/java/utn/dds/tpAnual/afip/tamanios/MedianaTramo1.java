package utn.dds.tpAnual.afip.tamanios;



/**
 * @author Rdiaz
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class MedianaTramo1 extends TamanioEmpresa{

	private final int jerarquia = 2;
	private final String nombre = "Mediana Tramo 1";
	private static MedianaTramo1 instance = new MedianaTramo1();
	
	public static MedianaTramo1 getInstance() {
		return instance;
	}
	
}