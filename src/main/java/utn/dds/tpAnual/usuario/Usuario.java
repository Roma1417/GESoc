package utn.dds.tpAnual.usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class Usuario {

	private List<Mensaje> bandejaMensajes;
	private int cantidadIntentos;
	private String contrasenia;
	private LocalDateTime fechaEspera;
	private String nombre;
	
	private final int LONGITUD_CONTRASENIA = 8;
	private final String PATH = "./src/main/resources/";
	private final String NOMBRE_ARCHIVO_CONTRASENIAS = "peoresContrasenias.txt";
	private final String NOMBRE_ARCHIVO_DICCIONARIO = "dictionaryPasswords.txt";
	private final String NOMBRE_ARCHIVO_PALABRAS_PROHIBIDAS = "palabrasProhibidas.txt";
	private final int CANTIDAD_REPETICIONES_CARACTER_MAXIMA = 3;
	
	public Usuario(){

	}
	
	public Usuario(String nombre, String contrasenia) {
		this.nombre = nombre;
		this.contrasenia = contrasenia;
	}

	public boolean validarContrasenia() {
		return validarLongitud() 
				&& validarNumerosLetras()
				&& !esPeorContrasenia()
				&& !esContraseniaDiccionario()
				&& !esContraseniaRepetitiva()
				&& !tieneNombreEnContrasenia()
				&& !esPalabraProhibida();
	}
	
	public void recibirMensaje(Mensaje mensaje) {
		if(bandejaMensajes == null) {
			bandejaMensajes = new ArrayList<Mensaje>();
		}
		bandejaMensajes.add(mensaje);
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
	
	private boolean esContraseniaRepetitiva() {
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

	private boolean esContraseniaDiccionario() {
		return buscarContraseniaEn(PATH + NOMBRE_ARCHIVO_DICCIONARIO, false);
	}

	private boolean esPeorContrasenia() {
		return buscarContraseniaEn(PATH + NOMBRE_ARCHIVO_CONTRASENIAS, true);
	}
	
	private boolean esPalabraProhibida() {
		return buscarContraseniaEn(PATH + NOMBRE_ARCHIVO_PALABRAS_PROHIBIDAS, true);
	}
	
	private boolean tieneNombreEnContrasenia() {
		return contrasenia.contains(nombre);
	}

	private boolean buscarContraseniaEn(String pathArchivo, boolean validacionObligatoria){
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
	public List<Mensaje> getBandejaMensajes() {
		return bandejaMensajes;
	}
}