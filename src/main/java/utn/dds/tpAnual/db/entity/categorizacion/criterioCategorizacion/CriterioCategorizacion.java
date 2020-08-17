package utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion;

import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.transaccion.Item;

public abstract class CriterioCategorizacion {
	
	private CriterioCategorizacion criterioHija; 
	
	public Categoria clasificarSegun(Item item){
		Categoria categoriaPropia = this.categorizar(item);

		if(criterioHija != null){
			Categoria categoriaDeMiHijo = criterioHija.clasificarSegun(item);
			categoriaPropia.setCategoriaHija(categoriaDeMiHijo);
		}
		return categoriaPropia;
	}

	protected abstract Categoria categorizar(Item item);
	
	public CriterioCategorizacion getCriterioHijo() {
		return criterioHija;
	}
	public void setCriterioHijo(CriterioCategorizacion criterioHijo) {
		this.criterioHija = criterioHijo;
	}
	
	

}