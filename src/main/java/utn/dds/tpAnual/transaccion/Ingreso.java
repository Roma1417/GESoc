package utn.dds.tpAnual.transaccion;

import utn.dds.tpAnual.categorizacion.criterioCategorizacion.CriterioCategorizacion;
import utn.dds.tpAnual.entidad.Entidad;

public class Ingreso extends Operacion{
	
	private Float total;
	private String descripcion;
	
	public Ingreso(DocumentoComercial documentoComercial, Entidad entidadRealizadora, int codigoOperacion,
			Float total, String descripcion, CriterioCategorizacion primerCriterio) {
		super(documentoComercial, entidadRealizadora, codigoOperacion, primerCriterio);
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
