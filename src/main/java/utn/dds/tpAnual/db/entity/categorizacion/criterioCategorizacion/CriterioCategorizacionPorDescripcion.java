package utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion;

import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.CategoriaNombreCorto;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.CategoriaNombreLargo;
import utn.dds.tpAnual.db.entity.transaccion.Item;

public class CriterioCategorizacionPorDescripcion extends CriterioCategorizacion{

	private static CriterioCategorizacionPorDescripcion instance = new CriterioCategorizacionPorDescripcion();

	private CriterioCategorizacionPorDescripcion() {
	}
	
	public static CriterioCategorizacionPorDescripcion getInstance() {
		return instance;
	}
	
	@Override
	protected Categoria categorizar(Item item) {
		return item.getDescripcion().length() > 20 ? CategoriaNombreLargo.getInstance() : CategoriaNombreCorto.getInstance();
	}

}
