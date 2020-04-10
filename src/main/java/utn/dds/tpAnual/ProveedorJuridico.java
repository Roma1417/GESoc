

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 17:39:45
 */
public class ProveedorJuridico extends Proveedor {

	private Long CUIT;
	private int direccionPostal;
	private String razonSocial;

	public ProveedorJuridico(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
}//end ProveedorJuridico