package utn.dds.tpAnual.db.entity.transaccion;

import utn.dds.tpAnual.db.entity.EntityInterface;
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
public abstract class Operacion implements EntityInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name ="ID")
	private Long operacionId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private DocumentoComercial documentoComercial;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Entidad entidadRealizadora;

	@Column(name = "CODIGO_OPERACION")
	private Integer codigoOperacion;

	@Column(name = "FECHA_CREACION")
	private LocalDate fecha;


	public Operacion(DocumentoComercial documentoComercial, Entidad entidadRealizadora,
					 Integer codigoOperacion) {
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

	public Integer getCodigoOperacion() {
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

	public void setCodigoOperacion(Integer codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public Long getId() {
		return operacionId;
	}
}