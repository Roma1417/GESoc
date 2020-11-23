package utn.dds.tpAnual.db.entity.transaccion;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.usuario.Usuario;

import javax.persistence.*;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
@Entity
@Table(name = "PRESUPUESTO")
public class Presupuesto extends Operacion {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "PRESUPUESTO_ID")
	private List<DetallePrecio> detallesPrecio;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Egreso egreso;

	public Presupuesto(DocumentoComercial documentoComercial, Entidad entidadRealizadora, int codigoOperacion,
					   List<DetallePrecio> detallesPrecio, Egreso egreso) {
		super(documentoComercial, entidadRealizadora, codigoOperacion);
		this.detallesPrecio = detallesPrecio;
		this.setEgreso(egreso);
	}

	public Presupuesto() {
	}

	public void setDetallesPrecio(List<DetallePrecio> detallesPrecio) {
		this.detallesPrecio = detallesPrecio;
	}

	public Float getTotal(){
		Float total = 0F;

		if (detallesPrecio != null) {
			for(DetallePrecio detalle : detallesPrecio) {
				total += detalle.getPrecioTotal();
			}
		}	
		return total;
	}
	
	public List<DetallePrecio> getDetallesPrecio(){
		return detallesPrecio;
	}

	public Egreso getEgreso() {
		return egreso;
	}

	public void setEgreso(Egreso egreso) {
		this.egreso = egreso;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			    .append("\ndetallesPrecio", detallesPrecio)
			    .toString();
	}
}