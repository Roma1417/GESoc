package utn.dds.tpAnual.consola;

import java.util.Scanner;

public class LectorEgreso extends Lector{
	private static Scanner scanner = new Scanner(System.in);
	private static LectorEgreso instance = new LectorEgreso();
	
	private LectorEgreso() {
	}
	
	public static LectorEgreso getInstance() {
			return instance;
	}
	
	@Override
	public void ejecutar(){

	}
}
