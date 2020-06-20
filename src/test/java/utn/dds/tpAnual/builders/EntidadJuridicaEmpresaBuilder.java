package utn.dds.tpAnual.builders;

import java.util.ArrayList;
import java.util.List;

import utn.dds.tpAnual.afip.Actividad;
import utn.dds.tpAnual.afip.VentaAnual;
import utn.dds.tpAnual.afip.tamanios.TamanioEmpresa;
import utn.dds.tpAnual.entidad.EntidadBase;
import utn.dds.tpAnual.entidad.EntidadJuridicaEmpresa;

public class EntidadJuridicaEmpresaBuilder {
	
	private Actividad actividad;
	private int cantidadPersonal;
	private List<VentaAnual> ventasAnuales = new ArrayList<VentaAnual>();
	private TamanioEmpresa tamanioEmpresa;
	private Long codigoIGJ;
	private Long CUIT;
	private int direccionPostal;
	private List<EntidadBase> entidadesBase = new ArrayList<EntidadBase>();
	private String razonSocial;
	private String nombre;
	
	public EntidadJuridicaEmpresaBuilder withNombre(String nombre){
        this.nombre = nombre;
        return this;
    }
	public EntidadJuridicaEmpresaBuilder withVentaAnual(VentaAnual ventaAnual){
        this.ventasAnuales.add(ventaAnual);
        return this;
    }
    public EntidadJuridicaEmpresa build(){
        return new EntidadJuridicaEmpresa(actividad, cantidadPersonal, ventasAnuales, nombre);
    }
}
