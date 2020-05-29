package utn.dds.tpAnual.consola;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utn.dds.tpAnual.compra.DetalleOperacion;
import utn.dds.tpAnual.compra.DetallePrecio;
import utn.dds.tpAnual.compra.Egreso;
import utn.dds.tpAnual.compra.Item;
import utn.dds.tpAnual.compra.Presupuesto;
import utn.dds.tpAnual.usuario.Usuario;
import utn.dds.tpAnual.validador.CriterioMenorPrecio;
import utn.dds.tpAnual.validador.Validador;


public class LectorEgreso extends Lector{
	private static Scanner scanner = new Scanner(System.in);
	private static LectorEgreso instance = new LectorEgreso();

	private LectorEgreso() {
	}

	public static LectorEgreso getInstance() {
		return instance;
	}

	@Override
	public void ejecutar(){
		System.out.println("Se dará de alta un Egreso:");	
		List<DetalleOperacion> detallesOperacion = getDetallesOperacion();
		Integer cantidadMinimaPresupuestos = getInteger("Ingrese la cantidad minima de presupuestos");
		List<Presupuesto> presupuestos = getPresupuestos(detallesOperacion);
		List<Usuario> revisores = getRevisores();		
		Egreso egreso = new Egreso(null, null, 0, null, null, null, cantidadMinimaPresupuestos, CriterioMenorPrecio.getInstance(), presupuestos, null, revisores);
		
		Validador validador = Validador.getInstance();
		if(validador.validarEgreso(egreso)) {
			System.out.println("La validación del egreso fue exitosa.");			
		}else {
			System.out.println("Falló la validación del egreso.");
		};
	}
	
	private Integer getInteger(String mensaje){
		System.out.println(mensaje);
		String val = scanner.nextLine();
		if (val.isEmpty()) {
			return null;
		}else {
			return Integer.valueOf(val);
		}
	}
	
	private String getString(String mensaje) {
		System.out.println(mensaje);
		String val = scanner.nextLine();
		if (val.isEmpty()) {
			return null;
		}else {
			return val;
		}
	}
	
	private Long getLong(String mensaje){
		System.out.println(mensaje);
		String val = scanner.nextLine();
		if (val.isEmpty()) {
			return null;
		}else {
			return Long.valueOf(val);
		}
	}
	
	private Float getFloat(String mensaje){
		System.out.println(mensaje);
		String val = scanner.nextLine();
		if (val.isEmpty()) {
			return null;
		}else {
			return Float.valueOf(val);
		}
	}
	
	private boolean preguntar(String tipoDato) {
		String eleccion = getString("¿Desea registrar un " + tipoDato + "? Y/N");
		return eleccion.equalsIgnoreCase("Y");
	}
	
	private List<DetalleOperacion> getDetallesOperacion(){
		List<DetalleOperacion> detallesOperacion = new ArrayList<DetalleOperacion>();
		System.out.println("Se tomara registro de los detalles de items del egreso.");
		while(true) {
			if(preguntar("detalle de item")) {
				DetalleOperacion detalleOperacion = getDetalleOperacion();
				detallesOperacion.add(detalleOperacion);
			} else {
				break;
			}
		}
		return detallesOperacion;
	}
	
	private Item getItem() {
		Long codigo = getLong("Ingrese el codigo del item");
		String descripcion = getString("Ingrese la descripción del item");
		if(codigo == null || descripcion == null) {
			return null;
		}
		return new Item (codigo, descripcion);
	}
	
	private DetalleOperacion getDetalleOperacion() {
		Integer cantidad = getInteger("Ingrese la cantidad de items");
		Item item = getItem();
		Float precio = getFloat("Ingrese el precio del item");
		if(cantidad == null || item == null || precio == null) {
			return null;
		}
		return new DetalleOperacion(item, precio, cantidad);
	}
	
	private Usuario getRevisor() {
		String nombre = getString("Ingrese el nombre del usuario");
		if(nombre == null) {
			return null;
		}
		return new Usuario(nombre, null);
	}
	
	private List<Usuario> getRevisores() {
		List<Usuario> revisores = new ArrayList<Usuario>();
		System.out.println("Se tomara registro de los revisores");
		while(true) {
			if(preguntar("revisor")) {
				Usuario revisor = getRevisor();
				revisores.add(revisor);
			} else {
				break;
			}
		}
		return revisores;
	}
	
	private List<Presupuesto> getPresupuestos(List<DetalleOperacion> detallesOperacion){
		List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
		System.out.println("Se tomara registro de los presupuestos");
		while(true) {
			if(preguntar("presupuesto")) {
				Presupuesto presupuesto = getPresupuesto(detallesOperacion);
				presupuestos.add(presupuesto);
			} else {
				break;
			}
		}
		return presupuestos;
	}
	
	private Presupuesto getPresupuesto(List<DetalleOperacion> detallesOperacion) {
		return new Presupuesto(null, null, 0, getDetallesPrecio(detallesOperacion));
	}
	
	private List<DetallePrecio> getDetallesPrecio(List<DetalleOperacion> detallesOperacion){
		List<DetallePrecio> detallesPrecio = new ArrayList<DetallePrecio>();
		for (DetalleOperacion detalleOperacion : detallesOperacion) {
			DetallePrecio detallePrecio = getDetallePrecio(detalleOperacion);
			detallesPrecio.add(detallePrecio);
		}
		return detallesPrecio;
	}
	
	private DetallePrecio getDetallePrecio(DetalleOperacion detalleOperacion) {
		Float precio = getFloat("Ingrese el valor presupuestado del item " + detalleOperacion.getDescripcionItem());
		return new DetallePrecio(detalleOperacion, precio);
	}
	
	private Presupuesto leerPresupuesto(int numeroPresupuesto) {
		return new Presupuesto(null, null, 0, leerDetallesPrecio(numeroPresupuesto));
	}
	
	private List<DetallePrecio> leerDetallesPrecio(int numeroPresupuesto){
		System.out.println("Complete los siguientes datos del presupuesto numero " + numeroPresupuesto);
		List<DetallePrecio> detallesPrecio = new ArrayList<DetallePrecio>();
		DetallePrecio detallePrecio;
		do{	
			detallePrecio = leerDetallePrecio();
			if (detallePrecio != null) {
				detallesPrecio.add(detallePrecio);
			}
		}while(detallePrecio != null);
		return null;
	}
	
	private DetallePrecio leerDetallePrecio() {
		System.out.println("Ingrese el codido del item:");
		Long codigo = scanner.nextLong();
		System.out.println("Ingrese precio del item:");
		Float precio = scanner.nextFloat();
		System.out.println("Ingrese la cantidad:");
		int cantidad = scanner.nextInt();
		System.out.println("Ingrese la descripcion del item:");
		String descripcion = scanner.nextLine();
		Item item = new Item(codigo, descripcion);
		DetalleOperacion detalleOperacion = new DetalleOperacion(item, precio, cantidad);
		DetallePrecio detallePrecio = new DetallePrecio(detalleOperacion, precio);
		return detallePrecio;
	}
}