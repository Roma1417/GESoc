package utn.dds.tpAnual.db.service.validador;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.configuracion.ConfiguracionEnum;
import utn.dds.tpAnual.db.entity.transaccion.DetallePrecio;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Presupuesto;
import utn.dds.tpAnual.db.service.jpaService.ConfiguracionService;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.service.jpaService.EgresoService;
import utn.dds.tpAnual.db.service.jpaService.UsuarioService;


/**
 * @author Mariano
 * @version 1.0
 * @created 26-abr.-2020 19:46:27
 */

@Service
public class ValidadorEgreso {

	@Autowired
	private ConfiguracionService configuracionService;

	@Autowired
	private EgresoService egresoService;

	@Autowired
	private UsuarioService usuarioService;

	private ValidadorEgreso(){

	}

	public void validarEgresos() {
		List<Egreso> egresos = egresoService.getEgresosSinValidar();
		for (Egreso egreso: egresos) {
			validarEgreso(egreso);
		}
	}

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


	private void notificarRevisores(Egreso egreso, boolean resultado) {
		String asunto = configuracionService.getValue(ConfiguracionEnum.ASUNTO_INICIO) + egreso.getCodigoOperacion();
		Set<Usuario> usuarios = egreso.getRevisores();

		this.enviarMensajes(usuarios, asunto,resultado ? configuracionService.getValue(ConfiguracionEnum.MENSAJE_CORRECTO)
				: configuracionService.getValue(ConfiguracionEnum.MENSAJE_ERRONEO));
	}

	private void enviarMensajes(Set<Usuario> usuarios, String asunto, String cuerpo) {
		Mensaje mensaje = new Mensaje(asunto, cuerpo);

		if(usuarios != null) {
			for (Usuario usuario : usuarios) {
				usuario.recibirMensaje(mensaje);
				usuarioService.save(usuario);
			}
		}
	}


	public boolean validarEgreso(Egreso egreso) {
		boolean validez = esEgresoValido(egreso);
		notificarRevisores(egreso, validez);
		egreso.setResultadoValidacion(validez ?
				ConfiguracionEnum.MENSAJE_CORRECTO.getDefaultValue():
				ConfiguracionEnum.MENSAJE_ERRONEO.getDefaultValue());
		egreso.setEsValidacionCorrecta(validez);
		egresoService.save(egreso);
		return validez;
	}

	private boolean esEgresoValido(Egreso egreso) {
		return cumpleMinimoPresupuesto(egreso) && cumpleBasarseEnPresupuesto(egreso) && cumpleCriterio(egreso);
	}

}