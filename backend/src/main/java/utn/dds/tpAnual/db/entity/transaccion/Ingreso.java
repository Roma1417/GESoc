package utn.dds.tpAnual.db.entity.transaccion;

import utn.dds.tpAnual.db.entity.entidad.Entidad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "INGRESO")
public class Ingreso extends Operacion {

	@Column(name = "TOTAL")
	private Float total;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@OneToMany(mappedBy="ingreso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Egreso> egresosAsociados;

	public Ingreso(DocumentoComercial documentoComercial, Entidad entidadRealizadora, int codigoOperacion,
				   Float total, String descripcion, List<Egreso> EgresosAsociados) {
		super(documentoComercial, entidadRealizadora, codigoOperacion);
		this.total = total; 
		this.descripcion = descripcion;
		setEgresosAsociados(EgresosAsociados);
	}

	public Ingreso() {
	}

	@Override
	public Float getTotal() {
		return total;
	}

	public List<Egreso> getEgresosAsociados() {
		if (egresosAsociados == null) {
			egresosAsociados = new ArrayList<>();
		}
		return egresosAsociados;
	}

	public void setEgresosAsociados(List<Egreso> egresosAsociados) {
		egresosAsociados = egresosAsociados;
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

	public boolean satisfaceRestante (Egreso egreso) {
		return getRestanteSinVincular() >= egreso.getTotal();
	}

	public Float getRestanteSinVincular () {
		return getTotal() - getValorVinculado();
	}

	private Float getValorVinculado(){
		return getEgresosAsociados() == null ? 0F :
				getEgresosAsociados().stream().map(egreso -> egreso.getTotal())
						.reduce(0F, (valor, acumulador) -> valor + acumulador);
	}


	public void vincularEgreso(Egreso egreso) {
		if (egresosAsociados == null) {
			egresosAsociados = new ArrayList<>();
		}
		egresosAsociados.add(egreso);
		egreso.setIngreso(this);
	}

	@Override
	public Long getId() {
		return null;
	}
}
