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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "ITEM")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO", nullable = false, length = 100)
	private Long codigo;

	@Column(name = "DESCRIPCION", length = 100)
	private String descripcion;

	@Transient
	private CriterioCategorizacion primerCriterio;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private Categoria categoria;

	public Item(Long codigo, String descripcion, CriterioCategorizacion primerCriterio){
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.primerCriterio = primerCriterio; 
	}
	
	public Item(Long codigo, String descripcion){
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public Item(){

	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
				.append("\ncodigo", codigo)
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