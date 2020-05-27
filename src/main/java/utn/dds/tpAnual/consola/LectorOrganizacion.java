package utn.dds.tpAnual.consola;

import java.util.Scanner;

public class LectorOrganizacion extends Lector{
	
	private static LectorOrganizacion instance = new LectorOrganizacion();
	
	private LectorOrganizacion() {
	}
	
	public static LectorOrganizacion getInstance() {
			return instance;
	}
	
	@Override
	public void ejecutar(){

	}
}
