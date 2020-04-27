package utn.dds.tpAnual.compra;

import java.util.List;

import utn.dds.tpAnual.entidad.Entidad;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class Presupuesto extends Operacion {

	private List<DetallePrecio> detalles;
	
	public Presupuesto(DocumentoComercial documentoComercial, Entidad entidadRealizadora, List<DetallePrecio> detalles) {
		super(documentoComercial, entidadRealizadora);
		this.detalles = detalles;
	}

	public Float getTotal(){
		return null;
	}
	
	public List<DetallePrecio> getDetalles(){
		return detalles;
	}
}