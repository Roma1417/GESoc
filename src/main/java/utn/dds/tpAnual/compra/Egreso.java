package utn.dds.tpAnual.compra;

import java.util.List;

import utn.dds.tpAnual.proveedor.Proveedor;
import utn.dds.tpAnual.usuario.Usuario;
import utn.dds.tpAnual.validador.CriterioCompra;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
public class Egreso extends OperacionEfectuada {

	private int cantidadPresupuestosMinimos;
	private CriterioCompra criterioCompra;
	private Proveedor proveedor;
	private List<Usuario> revisores;

	public Egreso(){

	}

}