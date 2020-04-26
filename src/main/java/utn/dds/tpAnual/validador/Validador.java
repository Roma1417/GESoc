package utn.dds.tpAnual.validador;

import java.util.List;

import utn.dds.tpAnual.compra.Egreso;
import utn.dds.tpAnual.usuario.Usuario;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
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
		if(!presupuestosMinimos)		
			return true;
		else if(!presupuestos)
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
		return presupuestos.stream().any(coincidenPrecios(pres -> pres.getDetalles(), detallesOperacion));
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
		Presupuesto presupuestoCumplidor = egreso.getCriterioCompra().getPresupuestoQueCumpla();
		List<DetalleOperacion> detallesOperacion = egreso.getDetalles();
		if(!presupuestoCumplidor)
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