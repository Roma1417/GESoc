package utn.dds.tpAnual.db.entity.transaccion;

import org.apache.commons.lang3.builder.ToStringBuilder;

import utn.dds.tpAnual.db.entity.EntityInterface;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion.CriterioCategorizacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */

@Entity
@Table(name = "ITEM")
public class Item implements EntityInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long itemId;

	@Column(name = "DESCRIPCION", length = 255)
	private String descripcion;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Categoria> categorias;

	public Item(Long itemId, String descripcion){
		this.itemId = itemId;
		this.descripcion = descripcion;
	}
	
	public Item(String descripcion){
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

	public void setCategoria(Categoria categoria) {
		this.categorias = Arrays.asList(categoria);
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

	@Override
	public Long getId() {
		return null;
	}
}