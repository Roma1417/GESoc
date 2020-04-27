package utn.dds.tpAnual.compra;

import java.time.LocalDate;
import java.util.List;

import utn.dds.tpAnual.entidad.Entidad;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public abstract class OperacionEfectuada extends Operacion {

	protected List<DetalleOperacion> detalles;
	protected LocalDate fechaOperacion;
	protected MedioPago medioPago;
	public DetalleOperacion m_DetalleOperacion;
	
	public OperacionEfectuada(DocumentoComercial documentoComercial, Entidad entidadRealizadora,
			List<DetalleOperacion> detalles, LocalDate fechaOperacion, MedioPago medioPago, DetalleOperacion m_DetalleOperacion) {
		super(documentoComercial, entidadRealizadora);
		this.detalles = detalles;
		this.fechaOperacion = fechaOperacion;
		this.medioPago = medioPago;
		this.m_DetalleOperacion = m_DetalleOperacion;
	}

	public Float getTotal(){
		return null;
	}
	
	public List<DetalleOperacion> getDetalles() {
		return detalles;
	}
}