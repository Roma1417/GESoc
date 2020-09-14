package utn.dds.tpAnual.db.entity.transaccion;

import org.apache.commons.lang3.builder.ToStringBuilder;

import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion.CriterioCategorizacion;

import javax.persistence.*;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */

@Entity
@Table(name = "ITEM")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long itemId;

	@Column(name = "DESCRIPCION", length = 255)
	private String descripcion;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private CriterioCategorizacion primerCriterio;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private Categoria categoria;

	public Item(Long itemId, String descripcion, CriterioCategorizacion primerCriterio){
		this.itemId = itemId;
		this.descripcion = descripcion;
		this.primerCriterio = primerCriterio; 
	}
	
	public Item(Long itemId, String descripcion){
		this.itemId = itemId;
		this.descripcion = descripcion;
	}

	public Item(){

	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CriterioCategorizacion getPrimerCriterio() {
		return primerCriterio;
	}

	public void setPrimerCriterio(CriterioCategorizacion primerCriterio) {
		this.primerCriterio = primerCriterio;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("\ncodigo", itemId)
			    .append("\ndescripcion", descripcion)
			    .toString();
	}
	
	public void recategorizar() {
		this.categoria = primerCriterio.clasificarSegun(this);
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
}