package utn.dds.tpAnual.validador;

import java.util.List;

import utn.dds.tpAnual.compra.Egreso;
import utn.dds.tpAnual.usuario.Mensaje;
import utn.dds.tpAnual.usuario.Usuario;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class Validador {
	
	private final String MENSAJE_CORRECTO = "Validacion realizada con Exito";
	private final String MENSAJE_ERRONEO = "Fallo de Validacion";
	private final String ASUNTO_INICIO = "Resultado Validacion Egreso: ";
	private static Validador instancia;
	
	private Validador(){

	}
	
	public static Validador getInstance() {
		if(instancia == null) {
			instancia = new Validador();
		}
		return instancia;
	}
	/**
	 * 
	 * @param egreso
	 */
	private boolean cumpleBasarseEnPresupuesto(Egreso egreso){
		return false;
	}

	/**
	 * 
	 * @param egreso
	 */
	private boolean cumpleCriterio(Egreso egreso){
		return false;
	}

	/**
	 * 
	 * @param egreso
	 */
	private boolean cumpleMinimoPresupuesto(Egreso egreso){
		return false;
	}
	
	/**
	 * 
	 * @param usuarios
	 */
	//TODO: Pasar a private
	public void notificarRevisores(Egreso egreso, boolean resultado) {
		String asunto = ASUNTO_INICIO + egreso.getCodigoOperacion();
		List<Usuario> usuarios = egreso.getRevisores();
		
		if(resultado == true) {
			this.enviarMensajes(usuarios, asunto, MENSAJE_CORRECTO);
		}else {
			this.enviarMensajes(usuarios, asunto, MENSAJE_ERRONEO);
		}
	}

	/**
	 * 
	 * @param egreso
	 */
	public boolean validarEgreso(Egreso egreso){
		return false;
	}
	
	private void enviarMensajes(List<Usuario> usuarios, String asunto, String cuerpo) {
		Mensaje mensaje = new Mensaje(asunto, cuerpo);
		
		if(usuarios!=null) {
			for (Usuario usuario : usuarios) {
				usuario.recibirMensaje(mensaje);
			}
		}
	}
}