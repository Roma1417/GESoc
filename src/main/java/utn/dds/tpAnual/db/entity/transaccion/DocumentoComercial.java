package utn.dds.tpAnual.db.entity.transaccion;

import utn.dds.tpAnual.db.entity.ubicacion.Pais;

import javax.persistence.*;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */

@Entity
@Table(name = "DOCUMENTO_COMERCIAL")
public class DocumentoComercial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long idDocumento;

	@Column(name = "TIPO_DOCUMENTO", nullable = false)
	private int tipoDocumento;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Pais pais;

	public DocumentoComercial(){

	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public int getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}