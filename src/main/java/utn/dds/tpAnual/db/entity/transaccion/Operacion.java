package utn.dds.tpAnual.db.entity.transaccion;

import utn.dds.tpAnual.db.entity.entidad.Entidad;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
@Entity
@Table(name = "OPERACION")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Operacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private Long operacionId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private DocumentoComercial documentoComercial;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Entidad entidadRealizadora;

	@Column(name = "CODIGO")
	private int codigoOperacion;

	@Column(name = "FECHA")
	private LocalDate fecha;


	public Operacion(DocumentoComercial documentoComercial, Entidad entidadRealizadora,
			int codigoOperacion) {
		this.documentoComercial = documentoComercial;
		this.entidadRealizadora = entidadRealizadora;
		this.codigoOperacion = codigoOperacion;
	}

	public Operacion() {
	}

	public Long getOperacionId() {
		return operacionId;
	}

	public void setOperacionId(Long operacionId) {
		this.operacionId = operacionId;
	}

	public int getCodigoOperacion() {
		return codigoOperacion;
	}

	public abstract Float getTotal();

	public DocumentoComercial getDocumentoComercial() {
		return documentoComercial;
	}

	public void setDocumentoComercial(DocumentoComercial documentoComercial) {
		this.documentoComercial = documentoComercial;
	}

	public Entidad getEntidadRealizadora() {
		return entidadRealizadora;
	}

	public void setEntidadRealizadora(Entidad entidadRealizadora) {
		this.entidadRealizadora = entidadRealizadora;
	}

	public void setCodigoOperacion(int codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}