package utn.dds.tpAnual.validador;

import java.util.List;

import utn.dds.tpAnual.compra.DetallePrecio;
import utn.dds.tpAnual.compra.Egreso;
import utn.dds.tpAnual.compra.Presupuesto;
import utn.dds.tpAnual.usuario.Mensaje;
import utn.dds.tpAnual.usuario.Usuario;


/**
 * @author Mariano
 * @version 1.0
 * @created 26-abr.-2020 19:46:27
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
	private boolean cumpleMinimoPresupuesto(Egreso egreso){
		int presupuestosMinimos = egreso.getCantidadPresupuestosMinimos();
		List<Presupuesto> presupuestos = egreso.getPresupuestos(); 
		
		return presupuestosMinimos == 0
				|| (presupuestos != null && presupuestosMinimos <= presupuestos.size());
	}
	
	/**
	 * 
	 * @param egreso
	 */
	private boolean cumpleBasarseEnPresupuesto(Egreso egreso){
		List<Presupuesto> presupuestos = egreso.getPresupuestos();
		
		return egreso.getCantidadPresupuestosMinimos() == 0
				|| (presupuestos.size() == egreso.getDetallesOperacion().size()
					&& presupuestos.stream().anyMatch(p -> coincidenPrecios(p.getDetallesPrecio())));
	}

	private boolean coincidenPrecios(List<DetallePrecio> detallesPrecio){
		return detallesPrecio.stream().allMatch(detPr -> detPr.getPrecio().equals(detPr.getDetalleOperacion().getPrecio()));
	}
	
	/**
	 * 
	 * @param egreso
	 */
	private boolean cumpleCriterio(Egreso egreso){
		
		return egreso.getCantidadPresupuestosMinimos() == 0
				|| egreso.getCriterioCompra() == null
				|| egreso.getCriterioCompra().getPresupuestoQueCumpla(egreso.getPresupuestos()) == null
				|| coincidenPrecios(egreso.getCriterioCompra().getPresupuestoQueCumpla(egreso.getPresupuestos()).getDetallesPrecio());
	}
	
	/**
	 * 
	 * @param usuarios
	 */
	private void notificarRevisores(Egreso egreso, boolean resultado) {
		String asunto = ASUNTO_INICIO + egreso.getCodigoOperacion();
		List<Usuario> usuarios = egreso.getRevisores();
		
		if(resultado) {
			this.enviarMensajes(usuarios, asunto, MENSAJE_CORRECTO);
		}
        else {
			this.enviarMensajes(usuarios, asunto, MENSAJE_ERRONEO);
		}
	}
	
	private void enviarMensajes(List<Usuario> usuarios, String asunto, String cuerpo) {
		Mensaje mensaje = new Mensaje(asunto, cuerpo);
		
		if(usuarios != null) {
			for (Usuario usuario : usuarios) {
				usuario.recibirMensaje(mensaje);
			}
		}
	}
	
	/**
	 * 
	 * @param egreso
	 */
	//TODO: Cambiar en el futuro a void cuando ya no se ejecute por consola
	public boolean validarEgreso(Egreso egreso){
		boolean validez = esEgresoValido(egreso);
		notificarRevisores(egreso, validez);
		return validez;
	}
	
	private boolean esEgresoValido(Egreso egreso) {
		return cumpleMinimoPresupuesto(egreso) && cumpleBasarseEnPresupuesto(egreso) && cumpleCriterio(egreso);
	}
	
}