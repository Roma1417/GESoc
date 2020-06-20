package utn.dds.tpAnual.transaccion;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class Item {

	private Long codigo;
	private String descripcion;

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
}