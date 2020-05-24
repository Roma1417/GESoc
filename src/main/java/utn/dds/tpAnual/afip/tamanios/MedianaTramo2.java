package utn.dds.tpAnual.afip.tamanios;



/**
 * @author Rdiaz
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class MedianaTramo2 extends TamanioEmpresa{

	private final int jerarquia = 3;
	private final String nombre = "Mediana tramo 2";
	private static MedianaTramo2 instance = new MedianaTramo2();
	
	private MedianaTramo2() {
		
	}
	
	public static MedianaTramo2 getInstance() {
		return instance;
	}
	
	@Override
	public int getJerarquia() {
		return jerarquia;
	}

	@Override
	public String getNombre() {
		return nombre;
	}
}