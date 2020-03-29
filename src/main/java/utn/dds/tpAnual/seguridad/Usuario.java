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
		// TODO Auto-generated method stub
		return false;
	}
	private boolean validarLongitud() {
		return contrasenia.length() >= LONGITUD_CONTRASENIA;
	}
}
