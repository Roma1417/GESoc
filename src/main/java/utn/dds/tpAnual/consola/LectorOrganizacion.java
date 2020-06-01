package utn.dds.tpAnual.consola;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import utn.dds.tpAnual.afip.Actividad;
import utn.dds.tpAnual.afip.RequisitoSectorEmpresa;
import utn.dds.tpAnual.afip.Sector;
import utn.dds.tpAnual.afip.VentaAnual;
import utn.dds.tpAnual.afip.tamanios.MedianaTramo1;
import utn.dds.tpAnual.afip.tamanios.MedianaTramo2;
import utn.dds.tpAnual.afip.tamanios.Micro;
import utn.dds.tpAnual.afip.tamanios.Pequenia;
import utn.dds.tpAnual.afip.tamanios.TamanioEmpresa;
import utn.dds.tpAnual.entidad.EntidadJuridica;
import utn.dds.tpAnual.entidad.EntidadJuridicaEmpresa;
import utn.dds.tpAnual.entidad.EntidadJuridicaOSC;

public class LectorOrganizacion extends Lector{
	
	private static LectorOrganizacion instance = new LectorOrganizacion();
	private static Scanner scanner = new Scanner(System.in);
	private RequisitoSectorEmpresa requisito1 = new RequisitoSectorEmpresa(100, 200F, Micro.getInstance());
	private RequisitoSectorEmpresa requisito2 = new RequisitoSectorEmpresa(500, 800F, Pequenia.getInstance());
	private RequisitoSectorEmpresa requisito3 = new RequisitoSectorEmpresa(10000, 800F, MedianaTramo1.getInstance());
	private RequisitoSectorEmpresa requisito4 = new RequisitoSectorEmpresa(100000, 8000F, MedianaTramo2.getInstance());
	private Sector financiero = new Sector("Financiero", Arrays.asList(requisito4, requisito3, requisito1, requisito2));
	private Actividad prestamista = new Actividad("Prestamista", financiero);
	private Sector industrial = new Sector("Indrustrial", Arrays.asList(requisito1, requisito2, requisito4, requisito3));
	private Actividad fabricacionAutos = new Actividad("Fabricacion de autos", industrial);
	
	private LectorOrganizacion() {
	}
	
	public static LectorOrganizacion getInstance() {
			return instance;
	}
	
	@Override
	public void ejecutar(){
		EntidadJuridica entidad = null;
		System.out.println("Se dará de alta una organización");
		String nombre = getNombre();
		Integer tipo = getTipo();
		if(tipo == 1) {
			Integer cantidadPersonal = getPersonal();
			List<VentaAnual> ventasAnuales = getVentasAnuales();
			Actividad actividad = getActividad();
			EntidadJuridicaEmpresa juridicaEmpresa = new EntidadJuridicaEmpresa(actividad, cantidadPersonal, ventasAnuales, nombre);
			juridicaEmpresa.recalcularTamanioEmpresa();
			entidad = juridicaEmpresa;
		}else if(tipo == 2) {
			EntidadJuridicaOSC juridicaOSC = new EntidadJuridicaOSC(nombre);
			entidad = juridicaOSC;
		}
		
		System.out.println("Se genero la empresa:\n" + entidad.toString());
	}
	
	private Integer getTipo() {
		System.out.println("Ingrese 1 para Entidad Juridica Empresa\n"
				+ "Ingrese 2 para Entidad Juridica OSC");
		String tipo = scanner.nextLine();
		return Integer.valueOf(tipo);
	}

	private String getNombre() {
		System.out.println("Ingrese nombre de la organizacion:");
		String nombre = scanner.nextLine();
		return nombre;
	}

	private Actividad getActividad() {
		System.out.println("Ingrese 0 para actividad prestamista del sector financiero \n"
				+ "Ingrese 1 para actividad fabricacion de autos del sector industrial");
		String seleccion = scanner.nextLine();
		List<Actividad> actividades = Arrays.asList(prestamista, fabricacionAutos);
		return actividades.get(Integer.valueOf(seleccion));
	}

	private List<VentaAnual> getVentasAnuales() {
		List<VentaAnual> ventasAnuales = new ArrayList();
		System.out.println("Ingresando Ventas anuales, Para finalizar presione \"ENTER\" sin nada escrito");
		
		while(true){
			System.out.println("Ingrese un año:");
			String anio = scanner.nextLine();
			if(anio.isEmpty()) {
				break;
			}
			System.out.println("Ingrese total anual:");
			String totalAnual = scanner.nextLine();
			if(totalAnual.isEmpty()) {
				break;
			}
			VentaAnual venta = new VentaAnual(Integer.valueOf(anio), Float.valueOf(totalAnual));
			ventasAnuales.add(venta);
		}
		
		return ventasAnuales;
	}

	private Integer getPersonal() {
		System.out.println("Ingrese personal:");
		String cantidadPersonal = scanner.nextLine();
		return Integer.valueOf(cantidadPersonal);
	}
	
}
