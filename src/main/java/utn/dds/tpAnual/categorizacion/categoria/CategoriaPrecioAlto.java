package utn.dds.tpAnual.categorizacion.categoria;

public class CategoriaPrecioAlto extends Categoria {

	private static CategoriaPrecioAlto instance;
	
	private CategoriaPrecioAlto(Categoria categoriaHija, String descripcion) {
		super(categoriaHija, descripcion);
	}
	
	public CategoriaPrecioAlto(String descripcion) {
		this(null, descripcion);
	}

	public static CategoriaPrecioAlto getInstance() {
		if(instance == null) {
			instance = new CategoriaPrecioAlto("Precio alto");
		}
		return instance;
	}

}
