package utn.dds.tpAnual.db.entity.entidad;

import org.apache.commons.lang3.builder.ToStringBuilder;
import utn.dds.tpAnual.db.service.vinculacion.criterioVinculacion.CriterioVinculacion;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "TAMANIO_EMPRESA")
public abstract class Entidad {

	private String nombre;

	private CriterioVinculacion criterioVinculacion;

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

	public CriterioVinculacion getCriterioVinculacion() {
		return criterioVinculacion;
	}

	public void setCriterioVinculacion(CriterioVinculacion criterioVinculacion) {
		this.criterioVinculacion = criterioVinculacion;
	}
}