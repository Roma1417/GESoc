package utn.dds.tpAnual.transaccion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import utn.dds.tpAnual.categorizacion.criterioCategorizacion.CriterioCategorizacion;
import utn.dds.tpAnual.entidad.Entidad;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public abstract class OperacionEfectuada extends Operacion {

	private List<DetalleOperacion> detallesOperacion;
	private LocalDate fechaOperacion;
	private MedioPago medioPago;
	
	public OperacionEfectuada(DocumentoComercial documentoComercial, Entidad entidadRealizadora, int codigoOperacion,
			List<DetalleOperacion> detallesOperacion, LocalDate fechaOperacion, MedioPago medioPago, 
			CriterioCategorizacion primerCriterio) {
		super(documentoComercial, entidadRealizadora, codigoOperacion, primerCriterio);
		this.detallesOperacion = detallesOperacion;
		this.fechaOperacion = fechaOperacion;
		this.medioPago = medioPago;
	}
	
	public Float getTotal(){
		Float total = 0F;
		if(detallesOperacion!=null) {
			for (DetalleOperacion detalle : detallesOperacion) {
				total += detalle.getTotal();
			}
		}
		return total;
	}
	
	public List<DetalleOperacion> getDetallesOperacion() {
		return detallesOperacion;
	}
	
	public void addDetalleOperacion(DetalleOperacion detalle) {
		if(detallesOperacion == null) {
			detallesOperacion = new ArrayList<DetalleOperacion>();
		}
		detallesOperacion.add(detalle);
	}
}