

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
	public Mensaje m_Mensaje;
	public UsuarioEntidad m_UsuarioEntidad;
	public Seguridad_Usuario m_Seguridad_Usuario;

	public Usuario(){

	}

	public void finalize() throws Throwable {

	}
	public void validarContrasenia(){

	}
}//end Usuario