package utn.dds.tpAnual.db.entity.categorizacion.criterioCompra;

import java.util.List;

import utn.dds.tpAnual.db.entity.EntityInterface;
import utn.dds.tpAnual.db.entity.transaccion.Presupuesto;

import javax.persistence.*;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "CRITERIO_COMPRA")
public abstract class CriterioCompra implements EntityInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long criterioCompraId;

	@Column(name = "NOMBRE", nullable = false, length = 100)
	private String nombreCriterio;

	public CriterioCompra() {
		
	}

	public Long getCriterioCompraId() {
		return criterioCompraId;
	}

	public void setCriterioCompraId(Long criterioCompraId) {
		this.criterioCompraId = criterioCompraId;
	}

	public String getNombreCriterio() {
		return nombreCriterio;
	}

	public void setNombreCriterio(String nombreCriterio) {
		this.nombreCriterio = nombreCriterio;
	}

	public abstract Presupuesto getPresupuestoQueCumpla(List<Presupuesto> presupuestos);
}