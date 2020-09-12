package utn.dds.tpAnual.criterioCompra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import utn.dds.tpAnual.db.entity.usuario.Usuario;

public class ValidadorContrasenia {

	private final int LONGITUD_CONTRASENIA = 8;
	private final String PATH = "./src/main/resources/";
	private final String NOMBRE_ARCHIVO_CONTRASENIAS = "peoresContrasenias.txt";
	private final String NOMBRE_ARCHIVO_DICCIONARIO = "dictionaryPasswords.txt";
	private final String NOMBRE_ARCHIVO_PALABRAS_PROHIBIDAS = "palabrasProhibidas.txt";
	private final int CANTIDAD_REPETICIONES_CARACTER_MAXIMA = 3;
	
	private static ValidadorContrasenia instance = new ValidadorContrasenia();
	
	private ValidadorContrasenia(){

	}
	
	public static ValidadorContrasenia getInstance() {
		return instance;
	}
	
	private boolean validarLongitud(String contrasenia) {
		return contrasenia.length() >= LONGITUD_CONTRASENIA;
	}
	
	private boolean validarNumerosLetras(String contrasenia) {
		return contieneNumerosYLetras(contrasenia);
	}
	
	private boolean contieneNumerosYLetras(String str) {
		String numerosRegex   = ".*[0-9].*";
		String letrasRegex = ".*[a-zA-Z].*";
		return str.matches(numerosRegex) && str.matches(letrasRegex);
	}
	
	private boolean tieneNombreEnContrasenia(String nombre, String contrasenia) {
		return contrasenia.contains(nombre);
	}
	
	private boolean esContraseniaRepetitiva(String contrasenia) {
		Integer cantidadRepeticiones = 1;
		Integer diferenciaEntreCaracteres = null;
		
		for(int i = 0; i < contrasenia.length() - 1 ; i++) {
			int diferenciaNueva = contrasenia.charAt(i) - contrasenia.charAt(i + 1);
			if(diferenciaEntreCaracteres != null && diferenciaEntreCaracteres == diferenciaNueva) {
				if(++cantidadRepeticiones == CANTIDAD_REPETICIONES_CARACTER_MAXIMA) {
					return true;
				}
			}else {
				diferenciaEntreCaracteres = diferenciaNueva;
				cantidadRepeticiones = 1;
			}
		}
		return false;
	}
	
	private boolean esPeorContrasenia(String contrasenia) {
		return buscarContrasenia(contrasenia, PATH + NOMBRE_ARCHIVO_CONTRASENIAS, true);
	}
	
	private boolean esContraseniaDiccionario(String contrasenia) {
		return buscarContrasenia(contrasenia, PATH + NOMBRE_ARCHIVO_DICCIONARIO, false);
	}
	
	private boolean esPalabraProhibida(String contrasenia) {
		return buscarContrasenia(contrasenia, PATH + NOMBRE_ARCHIVO_PALABRAS_PROHIBIDAS, true);
	}
	
	private boolean buscarContrasenia(String contrasenia, String pathArchivo, boolean validacionObligatoria){
		File contraseniasFile = new File(pathArchivo);
		try {
			if (contraseniasFile.exists()) {
				return archivoContienePalabra(contraseniasFile, contrasenia);
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
	
	public boolean esContraseniaValida(Usuario usuario) {
		String contrasenia = usuario.getContrasenia();
		return validarLongitud(contrasenia) 
				&& validarNumerosLetras(contrasenia)
				&& !tieneNombreEnContrasenia(usuario.getNombre(), contrasenia)
				&& !esContraseniaRepetitiva(contrasenia)
				&& !esPeorContrasenia(contrasenia)
				&& !esContraseniaDiccionario(contrasenia)
				&& !esPalabraProhibida(contrasenia);
	}
	
}
