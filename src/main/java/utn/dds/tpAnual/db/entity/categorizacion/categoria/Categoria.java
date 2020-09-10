package utn.dds.tpAnual.db.entity.categorizacion.categoria;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "CATEGORIA")
public abstract class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long idCategoria;

	@OneToOne(cascade = CascadeType.REFRESH)
	private Categoria categoriaHija;

	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;
	
	protected Categoria(Categoria categoriaHija, String descripcion) {
		super();
		this.categoriaHija = categoriaHija;
		this.descripcion = descripcion;
	}

	public Categoria() {

	}

	public Long getIdCategoria () {
		return idCategoria;
	}

	public void setIdCategoria (Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categoria getCategoriaHija() {
		return categoriaHija;
	}

	public void setCategoriaHija (Categoria categoriaHija) {
		this.categoriaHija = categoriaHija;
	}

	public String getDescripcion () {
		return descripcion;
	}

	public void setDescripcion (String descripcion) {
		this.descripcion = descripcion;
	}
	
}
