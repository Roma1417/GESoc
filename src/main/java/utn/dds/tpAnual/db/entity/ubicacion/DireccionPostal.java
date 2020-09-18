package utn.dds.tpAnual.db.entity.ubicacion;

import javax.persistence.*;

@Entity
@Table(name = "DIRECCION_POSTAL")
public class DireccionPostal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long direccionPostalId;

	@Column(name = "CALLE", nullable = false, length = 100)
	private String calle;

	@Column(name = "ALTURA", nullable = false)
	private int altura;

	@Column(name = "PISO")
	private int piso;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Ciudad ciudad;

	public DireccionPostal() {
	}

	public DireccionPostal(String calle, int altura, int piso, Ciudad ciudad) {
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Long getDireccionPostalId() {
		return direccionPostalId;
	}

	public void setDireccionPostalId(Long direccionPostalId) {
		this.direccionPostalId = direccionPostalId;
	}
}
