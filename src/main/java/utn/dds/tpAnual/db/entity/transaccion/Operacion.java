package utn.dds.tpAnual.db.entity.transaccion;

import utn.dds.tpAnual.entidad.Entidad;

import java.time.LocalDate;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public abstract class Operacion {

	private DocumentoComercial documentoComercial;
	private Entidad entidadRealizadora;
	private int codigoOperacion;
	private LocalDate fecha;


	public Operacion(DocumentoComercial documentoComercial, Entidad entidadRealizadora,
			int codigoOperacion) {
		this.documentoComercial = documentoComercial;
		this.entidadRealizadora = entidadRealizadora;
		this.codigoOperacion = codigoOperacion;
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