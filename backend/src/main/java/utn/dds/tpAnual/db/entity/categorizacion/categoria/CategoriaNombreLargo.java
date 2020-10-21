package utn.dds.tpAnual.db.entity.categorizacion.categoria;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "CategoriaNombreLargo")
@DiscriminatorValue("CategoriaNombreLargo")
public class CategoriaNombreLargo extends Categoria {

	private static CategoriaNombreLargo instance = new CategoriaNombreLargo("Nombre largo");

	public CategoriaNombreLargo() {

	}

	public CategoriaNombreLargo(Categoria categoriaHija, String descripcion) {
		super(categoriaHija, descripcion);
	}

	public CategoriaNombreLargo(String descripcion) {
		this(null, descripcion);
	}

	public static CategoriaNombreLargo getInstance() {
		return instance;
	}

	public static void setInstance(CategoriaNombreLargo instance) {
		CategoriaNombreLargo.instance = instance;
	}
}
