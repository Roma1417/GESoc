package utn.dds.tpAnual.validador;

import utn.dds.tpAnual.criterioCompra.ValidadorEgreso;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;

public class EgresosObserver {
	private static EgresosObserver instance = new EgresosObserver();

	private EgresosObserver() {
	}
	
	public static EgresosObserver getInstance() {
		return instance;
	}

	public void notificar(Egreso egreso) {
		ValidadorEgreso.getInstance().serNotificado(egreso);
	}
	
}
