package utn.dds.tpAnual.db.entity.transaccion;

import utn.dds.tpAnual.entidad.Entidad;

public class Ingreso extends Operacion{
	
	private Float total;
	private String descripcion;
	
	public Ingreso(DocumentoComercial documentoComercial, Entidad entidadRealizadora, int codigoOperacion,
			Float total, String descripcion) {
		super(documentoComercial, entidadRealizadora, codigoOperacion);
		this.total = total; 
		this.descripcion = descripcion; 
	}

	@Override
	public Float getTotal() {
		return total;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
