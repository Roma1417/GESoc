package utn.dds.tpAnual.db.entity.transaccion;

import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
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

	@Column(name = "NUMERO", nullable = false)
	private Integer numero;

	@Column(name = "TIPO_DOCUMENTO", nullable = false)
	private Integer tipoDocumento;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Pais pais;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Moneda moneda;

	public DocumentoComercial(){

	}

	public DocumentoComercial(int numero, int tipoDocumento, Pais pais, Moneda moneda) {
		this.numero = numero;
		this.tipoDocumento = tipoDocumento;
		this.pais = pais;
		this.moneda = moneda;
	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Integer getTipoDocumento() {
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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

}