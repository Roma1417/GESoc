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
	private final int LONGITUD_CONTRASENIA = 8;
	private final String NOMBRE_ARCHIVO_CONTRASENIAS = "peoresContrasenias.txt";
	private final String NOMBRE_ARCHIVO_DICCIONARIO = "dictionaryPasswords.txt";

	public Usuario(String nombre, String contrasenia) {
		this.nombre = nombre;
		this.contrasenia = contrasenia;
	}

	public boolean validarContrasenia() {
		return validarLongitud() 
				&& validarNumerosLetras()
				&& !esPeorContrasenia()
				&& !esContraseniaDiccionario();
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

	private boolean esContraseniaDiccionario() {
		return buscarContraseniaEn("./src/main/resources/" + NOMBRE_ARCHIVO_DICCIONARIO, false);
	}

	private boolean esPeorContrasenia() {
		return buscarContraseniaEn("./src/main/resources/" + NOMBRE_ARCHIVO_CONTRASENIAS, true);
	}

	private boolean buscarContraseniaEn(String pathArchivo, boolean validacionObligatoria){
		File peoresContraseniasFile = new File(pathArchivo);
		try {
			if (peoresContraseniasFile.exists()) {
				return archivoContienePalabra(peoresContraseniasFile, contrasenia);
			}
		}catch (IOException exception) {
			System.out.println("Hubo un error al buscar la contrasenia en el archivo" + pathArchivo);
		}
		return validacionObligatoria;
	}

	private boolean archivoContienePalabra(File archivoALeer, String palabra) throws IOException {
		FileReader lectorArchivo = new FileReader(archivoALeer);
		BufferedReader bufferedReader = new BufferedReader(lectorArchivo);
		String linea;

		while((linea = bufferedReader.readLine()) != null) {
			if(linea.equals(palabra)) {
				bufferedReader.close();
				return true;
			}
		}
		bufferedReader.close();
		return false;
	}
}
