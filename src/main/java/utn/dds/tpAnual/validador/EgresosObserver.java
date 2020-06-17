package utn.dds.tpAnual.validador;

import utn.dds.tpAnual.transaccion.Egreso;

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
