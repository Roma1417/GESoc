package utn.dds.tpAnual.builders;

import java.util.ArrayList;
import java.util.List;

import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.afip.RequisitoSectorEmpresa;
import utn.dds.tpAnual.db.entity.afip.Sector;
import utn.dds.tpAnual.db.entity.afip.VentaAnual;
import utn.dds.tpAnual.db.entity.afip.tamanios.Pequenia;
import utn.dds.tpAnual.db.entity.afip.tamanios.TamanioEmpresa;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.entidad.EntidadBase;
import utn.dds.tpAnual.db.entity.entidad.EntidadJuridicaEmpresa;
import utn.dds.tpAnual.db.entity.usuario.Admin;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.entity.usuario.UsuarioEntidad;

public class EntidadJuridicaEmpresaBuilder {
	
	private Actividad actividad;
	private int cantidadPersonal;
	private List<VentaAnual> ventasAnuales = new ArrayList<VentaAnual>();
	private List<UsuarioEntidad> usuariosEntidad = new ArrayList<>();
	private TamanioEmpresa tamanioEmpresa;
	private Long codigoIGJ;
	private String CUIT;
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
		EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresa(null, actividad, cantidadPersonal, ventasAnuales, nombre);
		entidad.setEntidadesBase(entidadesBase);
		entidad.setCUIT(CUIT);
		entidad.setRazonSocial(razonSocial);
		return entidad;
    }

	public EntidadJuridicaEmpresa buildEntidadCompletaSinRequisitos(){
		CUIT = "12345";
		codigoIGJ = 123131313L;
		cantidadPersonal = 30;
		actividad = new Actividad("Comercio", new Sector("Exterior",null));
		ventasAnuales.add(new VentaAnual(2019, 1000000F));
		ventasAnuales.add(new VentaAnual(2020, 3000000F));
		tamanioEmpresa = Pequenia.getInstance();
		entidadesBase.add(new EntidadBase("Base 1","EntidadBase"));
		entidadesBase.add(new EntidadBase("Base 2","EntidadBase2"));
		nombre = "La razón";

		Usuario usuario = new Usuario("Pepita", "pepa", "contraseña");
		EntidadJuridicaEmpresa entidad = build();
		UsuarioEntidad usuarioEntidad = new UsuarioEntidad(entidad, new Admin(),usuario);
		entidad.addUsuarioEntidad(usuarioEntidad);
		return entidad;
	}
}
