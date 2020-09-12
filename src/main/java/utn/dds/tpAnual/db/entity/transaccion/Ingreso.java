package utn.dds.tpAnual.db.entity.transaccion;

import utn.dds.tpAnual.db.entity.entidad.Entidad;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "INGRESO")
public class Ingreso extends Operacion{

	@Column(name = "TOTAL")
	private Float total;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Egreso> EgresosAsociados;


	public Ingreso(DocumentoComercial documentoComercial, Entidad entidadRealizadora, int codigoOperacion,
				   Float total, String descripcion) {
		super(documentoComercial, entidadRealizadora, codigoOperacion);
		this.total = total; 
		this.descripcion = descripcion; 
	}

	public Ingreso() {
	}

	@Override
	public Float getTotal() {
		return total;
	}

	public List<Egreso> getEgresosAsociados() {
		return EgresosAsociados;
	}

	public void setEgresosAsociados(List<Egreso> egresosAsociados) {
		EgresosAsociados = egresosAsociados;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}
