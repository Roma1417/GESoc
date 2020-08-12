package utn.dds.tpAnual.db.entity.afip;

import org.apache.commons.lang3.builder.ToStringBuilder;

import utn.dds.tpAnual.db.entity.afip.tamanios.TamanioEmpresa;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */

import javax.persistence.*;

@Entity
@Table(name = "ACTIVIDAD")
public class Actividad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long actividadId;

	@Column(name = "NOMBRE", unique = true, nullable = false, length = 100)
	private String nombreActividad;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Sector sector;

	public Actividad(String nombreActividad, Sector sector) {
		super();
		this.nombreActividad = nombreActividad;
		this.sector = sector;
	}

	public Actividad(){

	}

	public Long getActividadId() {
		return actividadId;
	}

	public void setActividadId(Long actividadId) {
		this.actividadId = actividadId;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	/**
	 * 
	 * @param cantidadEmpleados
	 * @param ventasAnuales
	 */
	public TamanioEmpresa getTamanioEmpresaPara(int cantidadEmpleados, Float ventasAnuales){
		return sector.getTamanioPara(cantidadEmpleados, ventasAnuales);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("nombre", nombreActividad)
			    .append("sector", sector.getNombre())
			    .toString();
	}
}