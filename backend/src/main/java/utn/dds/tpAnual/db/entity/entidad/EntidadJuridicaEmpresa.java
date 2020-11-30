package utn.dds.tpAnual.db.entity.entidad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.afip.VentaAnual;
import utn.dds.tpAnual.db.entity.afip.tamanios.TamanioEmpresa;
import utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion.CriterioVinculacion;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;

import javax.persistence.*;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
@Entity
@Table(name = "ENTIDAD_JURIDICA_EMPRESA")
public class EntidadJuridicaEmpresa extends EntidadJuridica {

	@Column(name = "CANTIDAD_PERSONAL")
	private int cantidadPersonal;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Actividad actividad;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ENTIDAD_JURIDICA_EMPRESA_ID")
	private List<VentaAnual> ventasAnuales;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private TamanioEmpresa tamanioEmpresa;

	public EntidadJuridicaEmpresa(){

	}
	
	public EntidadJuridicaEmpresa(TamanioEmpresa tamanioEmpresa, Actividad actividad, int cantidadPersonal, List<VentaAnual> ventasAnuales, String nombre) {
		super(nombre);
		this.actividad = actividad;
		this.cantidadPersonal = cantidadPersonal;
		this.ventasAnuales = ventasAnuales;
		this.tamanioEmpresa = tamanioEmpresa;
	}

	public TamanioEmpresa getTamanioEmpresa(){
		return this.tamanioEmpresa;
	}

	public int getCantidadPersonal() {
		return cantidadPersonal;
	}

	public void setCantidadPersonal(int cantidadPersonal) {
		this.cantidadPersonal = cantidadPersonal;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public List<VentaAnual> getVentasAnuales() {
		return ventasAnuales;
	}

	public void setVentasAnuales(List<VentaAnual> ventasAnuales) {
		this.ventasAnuales = ventasAnuales;
	}

	public void setTamanioEmpresa(TamanioEmpresa tamanioEmpresa) {
		this.tamanioEmpresa = tamanioEmpresa;
	}

	public Float getPromedioVentasParaAnios(int anios){
		int anioActual = LocalDate.now().getYear();
		Float total = 0F;
		Float cant = 0F;
		if(ventasAnuales != null){
			for (VentaAnual ventaAnual : ventasAnuales) {
				if(ventaAnual.getAnio() >= anioActual - anios) {
					total += ventaAnual.getVentasTotales();
					cant ++;
				}
			}
		}
		return total/anios;
	}


	public void addVentaAnual(VentaAnual venta) {
		if(ventasAnuales == null) {
			ventasAnuales = new ArrayList<VentaAnual>();
		}
		ventasAnuales.add(venta);
	}

	public void recalcularTamanioEmpresa(){
		this.tamanioEmpresa = actividad.getTamanioEmpresaPara(cantidadPersonal, getPromedioVentasParaAnios(3));
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("nombre", getNombre())
				.append("actividad", actividad.toString())
				.append("cantidad personal", String.valueOf(cantidadPersonal))
				.append("ventas anuales", ventasAnuales.toString())
				.append("tamaño empresa", tamanioEmpresa.getNombre())
			    .toString();
	}
	
}