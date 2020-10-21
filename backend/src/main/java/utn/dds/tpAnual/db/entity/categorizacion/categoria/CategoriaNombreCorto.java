package utn.dds.tpAnual.db.entity.categorizacion.categoria;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "CategoriaNombreCorto")
@DiscriminatorValue("CategoriaNombreCorto")
public class CategoriaNombreCorto extends Categoria {

	private static CategoriaNombreCorto instance = new CategoriaNombreCorto("Nombre corto");

	public CategoriaNombreCorto() {
	}

	public CategoriaNombreCorto(Categoria categoriaHija, String descripcion) {
		super(categoriaHija, descripcion);
	}

	public CategoriaNombreCorto(String descripcion) {
		this(null, descripcion);
	}

	public static CategoriaNombreCorto getInstance() {
		return instance;
	}

	public static void setInstance(CategoriaNombreCorto instance) {
		CategoriaNombreCorto.instance = instance;
	}
}
