package utn.dds.tpAnual.seguridad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String nombre;
	private String contrasenia;
	private int LONGITUD_CONTRASENIA = 8;
	private String NOMBRE_ARCHIVO_CONTRASENIAS = "peoresContrasenias.txt";

	public Usuario(String nombre, String contrasenia) {
		this.nombre = nombre;
		this.contrasenia = contrasenia;
	}

	public boolean validarContrasenia() {
		return validarLongitud() 
				&& validarNumerosLetras()
				&& !esPeorContrasenia();
	}

	private boolean validarNumerosLetras() {
		return contieneNumerosYLetras(contrasenia);
	}

	private boolean validarLongitud() {
		return contrasenia.length() >= LONGITUD_CONTRASENIA;
	}

	private boolean contieneNumerosYLetras(String str) {
		String numerosRegex   = ".*[0-9].*";
		String letrasRegex = ".*[a-zA-Z].*";
		return str.matches(numerosRegex) && str.matches(letrasRegex);
	}

	private boolean esPeorContrasenia() {
		List<String> listadoPeoresContrasenias;
		try {
			listadoPeoresContrasenias = getTopContrasenias();
		} catch (IOException e) {
			return false;
		}
		return listadoPeoresContrasenias.contains(contrasenia);
	}
	
	private List<String> getTopContrasenias() throws IOException {
		String pathArchivo = "./src/main/resources/" + NOMBRE_ARCHIVO_CONTRASENIAS;
		File peoresContraseniasFile = new File(pathArchivo);

		if (peoresContraseniasFile.exists()) {
			return getContenidoArchivo(peoresContraseniasFile);
		} else {
			throw new RuntimeException("El archivo de contraseñas no pudo ser encontrado en "
					+ pathArchivo);
		}
	}

	private List<String> getContenidoArchivo(File archivoALeer) throws IOException {
		FileReader lectorArchivo = new FileReader(archivoALeer);
		BufferedReader bufferedReader = new BufferedReader(lectorArchivo);
		List<String> lineasArchivo = new ArrayList<String>();
		String linea;

		while((linea = bufferedReader.readLine()) != null) {
			lineasArchivo.add(linea);
		}
		bufferedReader.close();
		
		return lineasArchivo;
	}
}
