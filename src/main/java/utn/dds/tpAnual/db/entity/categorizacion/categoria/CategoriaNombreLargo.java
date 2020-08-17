package utn.dds.tpAnual.db.entity.categorizacion.categoria;

import javax.persistence.Entity;

@Entity(name = "CategoriaNombreLargo")
public class CategoriaNombreLargo extends Categoria {

	private static CategoriaNombreLargo instance;

	public CategoriaNombreLargo() {

	}

	private CategoriaNombreLargo(Categoria categoriaHija, String descripcion) {
		super(categoriaHija, descripcion);
	}
	
	public CategoriaNombreLargo(String descripcion) {
		this(null, descripcion);
	}

	public static void setInstance(CategoriaNombreLargo instance) {
		CategoriaNombreLargo.instance = instance;
	}

	public static CategoriaNombreLargo getInstance() {
		if(instance == null) {
			instance = new CategoriaNombreLargo("Nombre largo");
		}
		return instance;
	}

}
