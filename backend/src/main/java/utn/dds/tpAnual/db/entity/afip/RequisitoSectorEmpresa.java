package utn.dds.tpAnual.db.entity.afip;

import utn.dds.tpAnual.db.entity.afip.tamanios.TamanioEmpresa;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */

import javax.persistence.*;

@Entity
@Table(name = "REQUISITO_SECTOR_EMPRESA")
public class RequisitoSectorEmpresa implements Comparable<RequisitoSectorEmpresa>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long requisitoSectorEmpresaId;

	@Column(name = "MAXIMO_EMPLEADOS", nullable = false)
	private int maximoEmpleados;

	@Column(name = "MAXIMO_FACTURACION", nullable = false)
	private Float maximoFacturacion;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TamanioEmpresa tamanioEmpresa;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Sector sector;
	
	public RequisitoSectorEmpresa(int maximoEmpleados, Float maximoFacturacion, TamanioEmpresa tamanioEmpresa) {
		this.maximoEmpleados = maximoEmpleados;
		this.maximoFacturacion = maximoFacturacion;
		this.tamanioEmpresa = tamanioEmpresa;
	}
	public RequisitoSectorEmpresa(){

	}

	public Long getRequisitoSectorEmpresaId() {
		return requisitoSectorEmpresaId;
	}

	public void setRequisitoSectorEmpresaId(Long requisitoSectorEmpresaId) {
		this.requisitoSectorEmpresaId = requisitoSectorEmpresaId;
	}

	public void setMaximoEmpleados(int maximoEmpleados) {
		this.maximoEmpleados = maximoEmpleados;
	}

	public void setMaximoFacturacion(Float maximoFacturacion) {
		this.maximoFacturacion = maximoFacturacion;
	}

	public int getMaximoEmpleados() {
		return maximoEmpleados;
	}

	public Float getMaximoFacturacion() {
		return maximoFacturacion;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public boolean excedeAlgunRequisito(int cantidadEmpleados, Float facturacionMaxima){
		return cantidadEmpleados > this.maximoEmpleados 
				|| facturacionMaxima > this.maximoFacturacion;
	}

	public TamanioEmpresa getTamanioEmpresa() {
		return tamanioEmpresa;
	}

	public void setTamanioEmpresa(TamanioEmpresa tamanioEmpresa) {
		this.tamanioEmpresa = tamanioEmpresa;
	}

	public int compareTo(RequisitoSectorEmpresa requisitoSectorEmpresa) {       
		int jerarquia = this.tamanioEmpresa.getJerarquia();
		int otraJerarquia = requisitoSectorEmpresa.getTamanioEmpresa().getJerarquia();
		return jerarquia == otraJerarquia ? 0 : 
			jerarquia > otraJerarquia ? 1 : -1;
	}    

}