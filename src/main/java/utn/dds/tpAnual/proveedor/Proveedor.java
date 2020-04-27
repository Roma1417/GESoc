package utn.dds.tpAnual.proveedor;



/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public abstract class Proveedor {

	protected int direccionPostal;

	public Proveedor(int direccionPostal) {
		this.direccionPostal = direccionPostal;
	}
}