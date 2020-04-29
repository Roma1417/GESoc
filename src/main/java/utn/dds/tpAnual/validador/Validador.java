package utn.dds.tpAnual.validador;

import java.util.List;
import java.util.stream.Collectors;

import utn.dds.tpAnual.compra.DetalleOperacion;
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
	
	public Validador(){

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
		
		if(presupuestosMinimos == 0) {
			return true;
		}
		else if(presupuestos == null) {
			return false;
		}
		return presupuestosMinimos <= presupuestos.size();
	}
	
	/**
	 * 
	 * @param egreso
	 */
	private boolean cumpleBasarseEnPresupuesto(Egreso egreso){
		List<Presupuesto> presupuestos = egreso.getPresupuestos();
		
		if(presupuestos.size() != egreso.getDetallesOperacion().size()) {
			return false;
		}
		return presupuestos.stream().anyMatch(p -> coincidenPrecios(p.getDetallesPrecio()));
	}

	private boolean coincidenPrecios(List<DetallePrecio> detallesPrecio){
		return detallesPrecio.stream().allMatch(detPr -> detPr.getPrecio().equals(detPr.getDetalleOperacion().getPrecio()));
	}
	
	/**
	 * 
	 * @param egreso
	 */
	private boolean cumpleCriterio(Egreso egreso){
		Presupuesto presupuestoCumplidor = egreso.getCriterioCompra().getPresupuestoQueCumpla(egreso.getPresupuestos());
		
		if(presupuestoCumplidor == null)
			return true;
		return coincidenPrecios(presupuestoCumplidor.getDetallesPrecio());
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

	/**
	 * 
	 * @param egreso
	 */
	public boolean validarEgreso(Egreso egreso){
		return cumpleMinimoPresupuesto(egreso) && cumpleBasarseEnPresupuesto(egreso) && cumpleCriterio(egreso);
	}
	
	private void enviarMensajes(List<Usuario> usuarios, String asunto, String cuerpo) {
		Mensaje mensaje = new Mensaje(asunto, cuerpo);
		
		if(usuarios!=null) {
			for (Usuario usuario : usuarios) {
				usuario.recibirMensaje(mensaje);
			}
		}
	}
	
	//--Test exclusive--
	
	protected boolean testearCumpleMinimo(Egreso egreso){
		return cumpleMinimoPresupuesto(egreso);
	}
	
	protected boolean testearCumpleBasarse(Egreso egreso){
		return cumpleBasarseEnPresupuesto(egreso);
	}
	
	protected boolean testearCumpleCriterio(Egreso egreso){
		return cumpleCriterio(egreso);
	}
	
	protected void testearNotificar(Egreso egreso, boolean resultado) {
		notificarRevisores(egreso, resultado);
	}
}