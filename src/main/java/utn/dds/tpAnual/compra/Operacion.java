package utn.dds.tpAnual.compra;

import utn.dds.tpAnual.entidad.Entidad;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public abstract class Operacion {

	private DocumentoComercial documentoComercial;
	private Entidad entidadRealizadora;

	public Operacion(){

	}

	public abstract Float getTotal();
}