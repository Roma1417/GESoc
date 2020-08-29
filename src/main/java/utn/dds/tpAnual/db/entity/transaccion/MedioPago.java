package utn.dds.tpAnual.db.entity.transaccion;

import javax.persistence.*;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "MEDIO_PAGO")
public class MedioPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO", nullable = false, length = 100)
	private Long codigo;

	@Column(name = "INSTRUMENTO_PAGO", nullable = false, length = 100)
	private String instrumentoPago;

	public MedioPago(){

	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getInstrumentoPago() {
		return instrumentoPago;
	}

	public void setInstrumentoPago(String instrumentoPago) {
		this.instrumentoPago = instrumentoPago;
	}
}