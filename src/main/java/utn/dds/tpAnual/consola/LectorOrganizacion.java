package utn.dds.tpAnual.consola;


public class LectorOrganizacion extends Lector{
	
	private static LectorOrganizacion instance = new LectorOrganizacion();
	
	private LectorOrganizacion() {
	}
	
	public static LectorOrganizacion getInstance() {
			return instance;
	}
	
	public void ejecutar(){
		
	};
}
