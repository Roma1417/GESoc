package utn.dds.tpAnual.validador;

import java.util.LinkedList;
import java.util.Queue;

import utn.dds.tpAnual.transaccion.Egreso;

public class EgresosContenedor {
	private Queue<Egreso> colaEgresos = new LinkedList<>();
	private ValidadorEgreso validador = ValidadorEgreso.getInstance();
	
	public void validarEgresos() {
		while(!colaEgresos.isEmpty()) {
			Egreso egreso = colaEgresos.poll();
			validador.validarEgreso(egreso);
		}
	}
	
	public void agregarEgreso(Egreso egreso) {
		colaEgresos.add(egreso);
	}
	
}
