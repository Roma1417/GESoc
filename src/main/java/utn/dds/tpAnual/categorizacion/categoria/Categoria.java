package utn.dds.tpAnual.categorizacion.categoria;

public abstract class Categoria {
	private Categoria categoriaHija;
	private String descripcion;
	
	protected Categoria(Categoria categoriaHija, String descripcion) {
		super();
		this.categoriaHija = categoriaHija;
		this.descripcion = descripcion;
	}
	
	public Categoria getCategoriaHija() {
		return categoriaHija;
	}
	public void setCategoriaHija(Categoria categoriaHija) {
		this.categoriaHija = categoriaHija;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
