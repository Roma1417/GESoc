package utn.dds.tpAnual.db.entity.afip.tamanios;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Rdiaz
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */

@Entity(name = "MedianaTramo1")
@DiscriminatorValue("MedianaTramo1")
public class MedianaTramo1 extends TamanioEmpresa{

	private final int jerarquia = 2;
	private final String nombre = "Mediana Tramo 1";
	private static MedianaTramo1 instance = new MedianaTramo1();
	
	public MedianaTramo1() {
		
	}

	public static void setInstance(MedianaTramo1 instance) {
		MedianaTramo1.instance = instance;
	}

	public static MedianaTramo1 getInstance() {
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