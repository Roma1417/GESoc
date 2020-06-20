package utn.dds.tpAnual.transaccion;

import org.apache.commons.lang3.builder.ToStringBuilder;

import utn.dds.tpAnual.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.categorizacion.criterioCategorizacion.CriterioCategorizacion;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class Item {

	private Long codigo;
	private String descripcion;
	private CriterioCategorizacion primerCriterio;
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