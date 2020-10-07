package utn.dds.tpAnual.consola;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import utn.dds.tpAnual.db.service.validador.ValidadorContrasenia;
import utn.dds.tpAnual.db.entity.usuario.Usuario;

public class LectorUsuario extends  Lector {
	private static Scanner scanner = new Scanner(System.in);
	private static LectorUsuario instance = new LectorUsuario();
	
	private LectorUsuario() {
		
	}

	@Autowired
	ValidadorContrasenia validadorContrasenia;

	public static LectorUsuario getInstance() {
			return instance;
	}
	
	@Override
	public void ejecutar(){
		System.out.println("Se dará de alta un Usuario: \nIngrese el nombre:");		
		String nombre = scanner.nextLine();
		System.out.println("Ingrese la contraseña:");
		String contrasenia = scanner.nextLine();
		Usuario usuario = new Usuario(nombre,nombre, contrasenia);
		System.out.println("Se ha generado el usuario: ");
		System.out.println(usuario);
		
		if(validadorContrasenia.esContraseniaValida(usuario)) {
			System.out.println("La validación del usuario fue exitosa.");			
		}else {
			System.out.println("Falló la validación del usuario.");
		}
	}
	
}
