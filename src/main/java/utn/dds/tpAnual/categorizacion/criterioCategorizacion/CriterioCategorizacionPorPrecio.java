package utn.dds.tpAnual.categorizacion.criterioCategorizacion;

import utn.dds.tpAnual.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.categorizacion.categoria.CategoriaPrecioAlto;
import utn.dds.tpAnual.categorizacion.categoria.CategoriaPrecioBajo;
import utn.dds.tpAnual.transaccion.Operacion;

public class CriterioCategorizacionPorPrecio extends CriterioCategorizacion{

	@Override
	protected Categoria categorizar(Operacion operacion) {
		return operacion.getTotal() > 1000F ? CategoriaPrecioAlto.getInstance() : CategoriaPrecioBajo.getInstance();
	}

}
