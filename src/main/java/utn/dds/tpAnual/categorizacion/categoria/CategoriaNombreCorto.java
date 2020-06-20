package utn.dds.tpAnual.categorizacion.categoria;

public class CategoriaNombreCorto extends Categoria {

	private static CategoriaNombreCorto instance;
	
	private CategoriaNombreCorto(Categoria categoriaHija, String descripcion) {
		super(categoriaHija, descripcion);
	}
	
	public CategoriaNombreCorto(String descripcion) {
		this(null, descripcion);
	}

	public static CategoriaNombreCorto getInstance() {
		if(instance == null) {
			instance = new CategoriaNombreCorto("Nombre corto");
		}
		return instance;
	}

}
