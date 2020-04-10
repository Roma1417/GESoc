

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 17:39:45
 */
public abstract class Operacion {

	private DocumentoComercial documentoComercial;
	private Entidad entidadRealizadora;
	public DocumentoComercial m_DocumentoComercial;

	public Operacion(){

	}

	public void finalize() throws Throwable {

	}
	public abstract Float getTotal();
}//end Operacion