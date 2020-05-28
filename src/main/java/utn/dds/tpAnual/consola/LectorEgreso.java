package utn.dds.tpAnual.consola;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import utn.dds.tpAnual.compra.DetalleOperacion;
import utn.dds.tpAnual.compra.DetallePrecio;
import utn.dds.tpAnual.compra.Item;
import utn.dds.tpAnual.compra.Presupuesto;

public class LectorEgreso extends Lector {
	private static Scanner scanner = new Scanner(System.in);
	private static LectorEgreso instance = new LectorEgreso();
	
	private LectorEgreso() {
	}
	
	public static LectorEgreso getInstance() {
			return instance;
	}
	
	@Override
	public void ejecutar() {
		Item itemTest = new Item(123L, "itemTest");
		DetalleOperacion unDetalleOperacion = new DetalleOperacion(itemTest, 10F, 3);
		DetalleOperacion otroDetalleOperacion = new DetalleOperacion(itemTest, 20F, 4);
		
		DetallePrecio unDetallePrecio = new DetallePrecio(unDetalleOperacion, 17F);
		DetallePrecio otroDetallePrecio = new DetallePrecio(otroDetalleOperacion, 12F);
		List<DetallePrecio> unosDetallesPrecio = Arrays.asList(unDetallePrecio, otroDetallePrecio);
		
		Presupuesto unPresupuesto = new Presupuesto(null, null, 1782, unosDetallesPrecio);
		
		System.out.println(unPresupuesto);
	}
	
}
