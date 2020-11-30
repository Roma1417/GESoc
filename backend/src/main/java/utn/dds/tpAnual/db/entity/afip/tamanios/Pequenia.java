package utn.dds.tpAnual.db.entity.afip.tamanios;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Rdiaz
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */

@Entity(name = "Pequenia")
@DiscriminatorValue("Pequenia")
public class Pequenia extends TamanioEmpresa{

	private final int jerarquia = 1;
	private final String nombre = "Pequenia";
	private static Pequenia instance = new Pequenia();
	
	public Pequenia() {
		
	}

	public static void setInstance(Pequenia instance) {
		Pequenia.instance = instance;
	}

	public static Pequenia getInstance() {
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