package utn.dds.tpAnual.categorizacion.categoria;

public class CategoriaNombreLargo extends Categoria {

	private static CategoriaNombreLargo instance;
	
	private CategoriaNombreLargo(Categoria categoriaHija, String descripcion) {
		super(categoriaHija, descripcion);
	}
	
	public CategoriaNombreLargo(String descripcion) {
		this(null, descripcion);
	}

	public static CategoriaNombreLargo getInstance() {
		if(instance == null) {
			instance = new CategoriaNombreLargo("Nombre largo");
		}
		return instance;
	}

}
