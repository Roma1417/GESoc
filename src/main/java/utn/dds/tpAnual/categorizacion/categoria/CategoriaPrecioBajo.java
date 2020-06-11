package utn.dds.tpAnual.categorizacion.categoria;

public class CategoriaPrecioBajo extends Categoria {

	private static CategoriaPrecioBajo instance;
	
	private CategoriaPrecioBajo(Categoria categoriaHija, String descripcion) {
		super(categoriaHija, descripcion);
	}
	
	public CategoriaPrecioBajo(String descripcion) {
		this(null, descripcion);
	}

	public static CategoriaPrecioBajo getInstance() {
		if(instance == null) {
			instance = new CategoriaPrecioBajo("Precio bajo");
		}
		return instance;
	}

}
