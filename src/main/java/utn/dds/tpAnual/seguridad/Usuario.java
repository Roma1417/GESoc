package utn.dds.tpAnual.seguridad;

public class Usuario {

	private String nombre;
	private String contrasenia;
	private int LONGITUD_CONTRASENIA = 8;
	
	public Usuario(String nombre, String contrasenia) {
		this.nombre = nombre;
		this.contrasenia = contrasenia;
	}
	
	public boolean validarContrasenia() {
		return validarLongitud() 
				&& validarNumerosLetras();
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
}
