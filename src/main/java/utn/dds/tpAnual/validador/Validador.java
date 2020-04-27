package utn.dds.tpAnual.validador;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import utn.dds.tpAnual.compra.DetalleOperacion;
import utn.dds.tpAnual.compra.DetallePrecio;
import utn.dds.tpAnual.compra.Egreso;
import utn.dds.tpAnual.compra.Presupuesto;
import utn.dds.tpAnual.usuario.Usuario;


/**
 * @author Mariano
 * @version 1.0
 * @created 26-abr.-2020 19:46:27
 */
public class Validador {

	public Validador(){

	}

	/**
	 * 
	 * @param egreso
	 */
	private boolean cumpleMinimoPresupuesto(Egreso egreso){
		int presupuestosMinimos = egreso.getCantidadPresupuestosMinimos();
		List<Presupuesto> presupuestos = egreso.getPresupuestos(); 
		if(presupuestosMinimos == 0)		
			return true;
		else if(presupuestos == null)
			return false;
		return presupuestosMinimos <= presupuestos.size();
	}
	
	/**
	 * 
	 * @param egreso
	 */
	private boolean cumpleBasarseEnPresupuesto(Egreso egreso){
		List<Presupuesto> presupuestos = egreso.getPresupuestos();
		List<DetalleOperacion> detallesOperacion = egreso.getDetalles();
		return presupuestos.stream().anyMatch(p -> coincidenPrecios(p.getDetalles(), detallesOperacion));
	}

	private boolean coincidenPrecios(List<DetallePrecio> detallesPrecio, List<DetalleOperacion> detallesOperacion) {
		List<Float> preciosPresupuesto = detallesPrecio.stream().map(dPr -> dPr.getPrecio()).collect(Collectors.toList());
		List<Float> preciosOperacion = detallesOperacion.stream().map(dOp -> dOp.getPrecioOperacion()).collect(Collectors.toList());
		return preciosPresupuesto.equals(preciosOperacion);
	}
	
	/**
	 * 
	 * @param egreso
	 */
	private boolean cumpleCriterio(Egreso egreso){
		Presupuesto presupuestoCumplidor = egreso.getCriterioCompra().getPresupuestoQueCumpla(egreso.getPresupuestos());
		List<DetalleOperacion> detallesOperacion = egreso.getDetalles();
		if(presupuestoCumplidor == null)
			return true;
		return coincidenPrecios(presupuestoCumplidor.getDetalles(), detallesOperacion);
	}

	/**
	 * 
	 * @param usuarios
	 */
	private void notificarRevisores(List<Usuario> usuarios){

	}

	/**
	 * 
	 * @param egreso
	 */
	public boolean validarEgreso(Egreso egreso){
		return false;
	}
}