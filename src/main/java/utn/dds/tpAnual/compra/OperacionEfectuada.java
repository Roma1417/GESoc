

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

	public void finalize() throws Throwable {
		super.finalize();
	}
	public Float getTotal(){
		return null;
	}
}//end OperacionEfectuada