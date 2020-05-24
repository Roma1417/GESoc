package utn.dds.tpAnual.proveedor;



/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class ProveedorPersona extends Proveedor {

	private Long dni;
	private String nombre;

	public ProveedorPersona(int direccionPostal, Long dni, String nombre) {
		super(direccionPostal);
		this.dni = dni;
		this.nombre = nombre;
	}
}