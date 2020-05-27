package utn.dds.tpAnual.consola;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Consola {
	private static List<Lector> arr = Arrays.asList(
			LectorUsuario.getInstance(), 
			LectorEgreso.getInstance(), 
			LectorOrganizacion.getInstance());  
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		boolean repetir = false;
		do {
			try {
				leerYEjecutar();
				repetir = false;
			}catch(Exception e) {
				System.out.println("Ha ingresado un valor inválido. Reintente nuevamente.");
				repetir = true;
			}
		}while(repetir);

	}

	public static void leerYEjecutar() {
		System.out.println("Por favor ingrese un numero:\n" +
				"  0 - Para ingresar un Usuario.\n" +
				"  1 - Para ingresar un Egreso.\n" +
				"  2 - Para ingresar una Organizacion.");
		
		String input = scanner.nextLine();
		arr.get(Integer.valueOf(input)).ejecutar();
	}

}
