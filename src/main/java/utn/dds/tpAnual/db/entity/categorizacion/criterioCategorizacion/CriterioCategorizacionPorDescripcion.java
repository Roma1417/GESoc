package utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion;

import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.CategoriaNombreCorto;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.CategoriaNombreLargo;
import utn.dds.tpAnual.db.entity.transaccion.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "CriterioCategPorDescripcion")
@DiscriminatorValue("CriterioCategPorDescripcion")
public class CriterioCategorizacionPorDescripcion extends CriterioCategorizacion{

	private static CriterioCategorizacionPorDescripcion instance = new CriterioCategorizacionPorDescripcion();
	private final String descripcion = "Categorizacion por descripción";

	private CriterioCategorizacionPorDescripcion() {
	}
	
	public static CriterioCategorizacionPorDescripcion getInstance() {
		return instance;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	protected Categoria categorizar(Item item) {
		return item.getDescripcion().length() > 20 ? CategoriaNombreLargo.getInstance() : CategoriaNombreCorto.getInstance();
	}

}
