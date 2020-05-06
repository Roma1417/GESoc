package utn.dds.tpAnual.proveedor;


/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class ProveedorJuridico extends Proveedor {

	private Long cuit;
	private String razonSocial;

	public ProveedorJuridico(int direccionPostal, Long cuit, String razonSocial) {
		super(direccionPostal);
		this.cuit = cuit;
		this.razonSocial = razonSocial;
	}
}