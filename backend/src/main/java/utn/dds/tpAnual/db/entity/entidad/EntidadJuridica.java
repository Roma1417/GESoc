package utn.dds.tpAnual.db.entity.entidad;

import utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion.CriterioVinculacion;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;
import utn.dds.tpAnual.db.entity.usuario.UsuarioEntidad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
@Entity
@Table(name = "ENTIDAD_JURIDICA")
public abstract class EntidadJuridica extends Entidad {

	@Column(name = "CODIGO_IGJ", length = 100)
	private Long codigoIGJ;

	@Column(name = "CUIT", length = 100)
	private String CUIT;

	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private DireccionPostal direccionPostal;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ENTIDAD_JURIDICA_ID")
	private List<EntidadBase> entidadesBase;

	public EntidadJuridica(String nombre) {
		super(nombre);
	}

	public EntidadJuridica() {
	}

	public Long getCodigoIGJ() {
		return codigoIGJ;
	}

	public void setCodigoIGJ(Long codigoIGJ) {
		this.codigoIGJ = codigoIGJ;
	}

	public String getCUIT() {
		return CUIT;
	}

	public void setCUIT(String CUIT) {
		this.CUIT = CUIT;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public DireccionPostal getDireccionPostal() {
		return direccionPostal;
	}

	public void setDireccionPostal(DireccionPostal direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

	public List<EntidadBase> getEntidadesBase() {
		if(entidadesBase == null){
			entidadesBase = new ArrayList();
		}
		return entidadesBase;
	}

	public void setEntidadesBase(List<EntidadBase> entidadesBase) {
		this.entidadesBase = entidadesBase;
	}
}