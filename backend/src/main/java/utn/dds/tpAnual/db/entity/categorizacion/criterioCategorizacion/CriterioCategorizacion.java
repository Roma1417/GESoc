package utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion;

import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.transaccion.Item;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "CRITERIO_CATEGORIZACION")
public abstract class CriterioCategorizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long criterioCategorizacionId;

	@Column(name = "descripcion")
	private String descripcion;

	@OneToOne(cascade = CascadeType.REFRESH)
	private CriterioCategorizacion criterioHija;
	
	public Categoria clasificarSegun(Item item){
		Categoria categoriaPropia = this.categorizar(item);

		if(criterioHija != null){
			Categoria categoriaDeMiHijo = criterioHija.clasificarSegun(item);
			categoriaPropia.setCategoriaHija(categoriaDeMiHijo);
		}
		return categoriaPropia;
	}

	protected abstract Categoria categorizar(Item item);
	
	public CriterioCategorizacion getCriterioHijo() {
		return criterioHija;
	}

	public void setCriterioHijo(CriterioCategorizacion criterioHijo) {
		this.criterioHija = criterioHijo;
	}

	public Long getCriterioCategorizacionId() {
		return criterioCategorizacionId;
	}

	public void setCriterioCategorizacionId(Long criterioCategorizacionId) {
		this.criterioCategorizacionId = criterioCategorizacionId;
	}

	public CriterioCategorizacion getCriterioHija() {
		return criterioHija;
	}

	public void setCriterioHija(CriterioCategorizacion criterioHija) {
		this.criterioHija = criterioHija;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}