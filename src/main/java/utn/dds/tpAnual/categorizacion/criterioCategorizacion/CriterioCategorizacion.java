package utn.dds.tpAnual.categorizacion.criterioCategorizacion;

import utn.dds.tpAnual.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.transaccion.Operacion;

public abstract class CriterioCategorizacion {
	
	private CriterioCategorizacion criterioHija; 
	
	public Categoria clasificarSegun(Operacion operacion){
		Categoria categoriaPropia = this.categorizar(operacion);

		if(criterioHija != null){
			Categoria categoriaDeMiHijo = criterioHija.clasificarSegun(operacion);
			categoriaPropia.setCategoriaHija(categoriaDeMiHijo);
		}
		return categoriaPropia;
	}

	protected abstract Categoria categorizar(Operacion operacion);
	
	public CriterioCategorizacion getCriterioHijo() {
		return criterioHija;
	}
	public void setCriterioHijo(CriterioCategorizacion criterioHijo) {
		this.criterioHija = criterioHijo;
	}
	
	

}