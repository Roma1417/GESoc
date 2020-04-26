package utn.dds.tpAnual.compra;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public abstract class OperacionEfectuada extends Operacion {

	private List<DetalleOperacion> detalles;
	private LocalDate fechaOperacion;
	private MedioPago medioPago;
	public DetalleOperacion m_DetalleOperacion;

	public OperacionEfectuada(){

	}

	public Float getTotal(){
		return null;
	}
	
	public List<DetalleOperacion> getDetalles() {
		return detalles;
	}
}