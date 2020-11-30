package utn.dds.tpAnual.db.entity.transaccion;

import javax.persistence.*;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */

@Entity
@Table(name = "MEDIO_PAGO")
public class MedioPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long medioPagoId;

	@Column(name = "INSTRUMENTO_PAGO", nullable = false, length = 100)
	private String instrumentoPago;

	public MedioPago(){

	}

	public MedioPago(String instrumentoPago) {
		this.instrumentoPago = instrumentoPago;
	}

	public Long getMedioPagoId() {
		return medioPagoId;
	}

	public void setMedioPagoId(Long medioPagoId) {
		this.medioPagoId = medioPagoId;
	}

	public String getInstrumentoPago() {
		return instrumentoPago;
	}

	public void setInstrumentoPago(String instrumentoPago) {
		this.instrumentoPago = instrumentoPago;
	}
}