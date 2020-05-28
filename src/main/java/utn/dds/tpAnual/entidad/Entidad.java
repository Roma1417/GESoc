package utn.dds.tpAnual.entidad;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
public abstract class Entidad {

	private String nombre;

	public Entidad(){

	}

	public Entidad(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	@Override 
	public String toString() {
		return new ToStringBuilder(this)
				.append("nombre", nombre)
			    .toString();
	}

}