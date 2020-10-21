package utn.dds.tpAnual.db.entity.entidad;

import org.apache.commons.lang3.builder.ToStringBuilder;
import utn.dds.tpAnual.db.entity.usuario.UsuarioEntidad;
import utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion.CriterioVinculacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ENTIDAD")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Entidad {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID", unique = true, nullable = false)
	private Long entidadId;

	@Column(name = "NOMBRE", unique = true, nullable = false)
	private String nombre;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private CriterioVinculacion criterioVinculacion;

	@OneToMany(mappedBy="entidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UsuarioEntidad> usuariosEntidad;

	public Entidad(String nombre, CriterioVinculacion criterioVinculacion){

	}

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

	public List<UsuarioEntidad> getUsuariosEntidad() {
		return usuariosEntidad;
	}

	public void setUsuariosEntidad(List<UsuarioEntidad> usuariosEntidad) {
		this.usuariosEntidad = usuariosEntidad;
	}

	public void addUsuarioEntidad (UsuarioEntidad usuarioEntidad){
		if(usuariosEntidad == null) {
			this.usuariosEntidad = new ArrayList<>();
		}
		this.usuariosEntidad.add(usuarioEntidad);
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