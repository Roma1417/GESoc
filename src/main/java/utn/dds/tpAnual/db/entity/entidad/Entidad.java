package utn.dds.tpAnual.db.entity.entidad;

import org.apache.commons.lang3.builder.ToStringBuilder;
import utn.dds.tpAnual.db.entity.usuario.UsuarioEntidad;
import utn.dds.tpAnual.db.service.vinculacion.criterioVinculacion.CriterioVinculacion;

import javax.persistence.*;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "ENTIDAD")
public abstract class Entidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long entidadId;

	@Column(name = "NOMBRE", unique = true, nullable = false)
	private String nombre;

	@Transient
	private CriterioVinculacion criterioVinculacion;

	@OneToMany(mappedBy="entidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UsuarioEntidad> usuariosEntidad;

	public Entidad(){

	}

	public Long getEntidadId() {
		return entidadId;
	}

	public void setEntidadId(Long entidadId) {
		this.entidadId = entidadId;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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