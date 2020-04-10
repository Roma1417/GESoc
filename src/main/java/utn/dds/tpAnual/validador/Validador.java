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