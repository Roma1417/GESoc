package utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion;

import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.transaccion.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "CRITERIO_CATEGORIZACION")
public class CriterioCategorizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long criterioCategorizacionId;

	@Column(name = "descripcion")
	private String descripcion;

	@OneToMany(mappedBy="criterioCategorizacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Categoria> categorias;

	public CriterioCategorizacion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getCriterioCategorizacionId() {
		return criterioCategorizacionId;
	}

	public CriterioCategorizacion() {
	}

	public void setCriterioCategorizacionId(Long criterioCategorizacionId) {
		this.criterioCategorizacionId = criterioCategorizacionId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void addCategoria(Categoria categoria) {
		if (categorias == null){
			categorias = new ArrayList<>();
		}
		categorias.add(categoria);
	}
}