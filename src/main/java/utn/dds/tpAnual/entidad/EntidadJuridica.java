package utn.dds.tpAnual.entidad;

import java.util.List;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
public abstract class EntidadJuridica extends Entidad {

	private Long codigoIGJ;
	private Long CUIT;
	private int direccionPostal;
	private List<EntidadBase> entidadesBase;
	private String razonSocial;

	public EntidadJuridica(){

	}

	public EntidadJuridica(String nombre) {
		super(nombre);
	}

}