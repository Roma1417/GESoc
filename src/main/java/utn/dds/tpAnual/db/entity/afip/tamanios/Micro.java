package utn.dds.tpAnual.db.entity.afip.tamanios;



/**
 * @author Rdiaz
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class Micro extends TamanioEmpresa{

	private final int jerarquia = 0;
	private final String nombre = "Micro";
	private static Micro instance = new Micro();
	
	private Micro() {
		
	}
	
	public static Micro getInstance() {
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