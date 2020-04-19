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

	public Egreso(int codigoOperacion, int cantidadPresupuestosMinimos, CriterioCompra criterioCompra,
			Proveedor proveedor, List<Usuario> revisores){
		super(codigoOperacion);
		this.cantidadPresupuestosMinimos = cantidadPresupuestosMinimos;
		this.criterioCompra = criterioCompra;
		this.proveedor = proveedor;
		this.revisores = revisores;
		
	}

	public List<Usuario> getRevisores() {
		return revisores;
	}

}