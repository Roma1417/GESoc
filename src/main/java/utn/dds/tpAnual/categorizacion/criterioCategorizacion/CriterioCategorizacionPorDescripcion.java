package utn.dds.tpAnual.categorizacion.criterioCategorizacion;

import utn.dds.tpAnual.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.categorizacion.categoria.CategoriaNombreCorto;
import utn.dds.tpAnual.categorizacion.categoria.CategoriaNombreLargo;
import utn.dds.tpAnual.transaccion.Item;

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
