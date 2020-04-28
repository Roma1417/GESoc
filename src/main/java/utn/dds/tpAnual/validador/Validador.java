package utn.dds.tpAnual.validador;

import java.util.List;
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
	
	//

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
	private void notificarRevisores(List<Usuario> usuarios){

	}

	/**
	 * 
	 * @param egreso
	 */
	public boolean validarEgreso(Egreso egreso){
		return cumpleMinimoPresupuesto(egreso) && cumpleBasarseEnPresupuesto(egreso) && cumpleCriterio(egreso);
	}
	
	//Test exclusive
	
	protected boolean validarCumpleMinimo(Egreso egreso){
		return cumpleMinimoPresupuesto(egreso);
	}
	
	protected boolean validarCumpleBasarse(Egreso egreso){
		return cumpleBasarseEnPresupuesto(egreso);
	}
	
	protected boolean validarCumpleCriterio(Egreso egreso){
		return cumpleCriterio(egreso);
	}
}