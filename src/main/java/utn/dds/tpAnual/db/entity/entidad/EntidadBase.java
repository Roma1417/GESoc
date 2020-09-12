package utn.dds.tpAnual.db.entity.entidad;


import javax.persistence.*;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
@Entity(name = "EntidadBase")
@DiscriminatorValue("EntidadBase")
public class EntidadBase extends Entidad {

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private EntidadJuridica entidadJuridica;

	public EntidadBase(){

	}

}