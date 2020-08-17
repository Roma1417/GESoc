package utn.dds.tpAnual.db.entity.categorizacion.categoria;

import javax.persistence.Entity;

@Entity(name = "CategoriaNombreCorto")
public class CategoriaNombreCorto extends Categoria {

	private static CategoriaNombreCorto instance;

	public CategoriaNombreCorto() {

	}

	private CategoriaNombreCorto(Categoria categoriaHija, String descripcion) {
		super(categoriaHija, descripcion);
	}
	
	public CategoriaNombreCorto(String descripcion) {
		this(null, descripcion);
	}

	public static void setInstance(CategoriaNombreCorto instance) {
		CategoriaNombreCorto.instance = instance;
	}

	public static CategoriaNombreCorto getInstance() {
		if(instance == null) {
			instance = new CategoriaNombreCorto("Nombre corto");
		}
		return instance;
	}

}
