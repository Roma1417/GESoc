package utn.dds.tpAnual.compra;

import java.time.LocalDate;
import java.util.ArrayList;
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

	public OperacionEfectuada(){

	}

	public Float getTotal(){
		Float total = 0F;
		for (DetalleOperacion detalle : detalles) {
			total += detalle.getTotal();
		}
		return total;
	}
	
	public void addDetalleOperacion(DetalleOperacion detalle) {
		if(detalles == null) {
			detalles = new ArrayList<DetalleOperacion>();
		}
		detalles.add(detalle);
	}
}