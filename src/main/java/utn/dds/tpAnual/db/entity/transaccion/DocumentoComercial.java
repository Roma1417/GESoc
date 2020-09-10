package utn.dds.tpAnual.db.entity.transaccion;

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

	@Column(name = "PAIS", nullable = false, length = 100)
	private String pais;

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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}