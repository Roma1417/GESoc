package utn.dds.tpAnual.afip.builders;

import java.util.Arrays;
import java.util.List;

import utn.dds.tpAnual.transaccion.DetalleOperacion;
import utn.dds.tpAnual.transaccion.DetallePrecio;
import utn.dds.tpAnual.transaccion.Egreso;
import utn.dds.tpAnual.transaccion.Item;
import utn.dds.tpAnual.transaccion.Presupuesto;
import utn.dds.tpAnual.usuario.Usuario;
import utn.dds.tpAnual.validador.CriterioMenorPrecio;

public class EgresoBuilder {
	
	//DetalleOperacion
	private static Item itemTest = new Item(123L, "itemTest");
	private static DetalleOperacion unDetalleOperacion = new DetalleOperacion(itemTest, 10F, 3);
	private static DetalleOperacion otroDetalleOperacion = new DetalleOperacion(itemTest, 20F, 4);
	private static List<DetalleOperacion> unosDetallesOperacion = Arrays.asList(unDetalleOperacion, unDetalleOperacion);
	private static List<DetalleOperacion> otrosDetallesOperacion = Arrays.asList(unDetalleOperacion, otroDetalleOperacion);
		
	//DetallePrecio
	private static DetallePrecio unDetallePrecio = new DetallePrecio(unDetalleOperacion, 10F);
	private static DetallePrecio otroDetallePrecio = new DetallePrecio(otroDetalleOperacion, 12F);
	private static List<DetallePrecio> unosDetallesPrecio = Arrays.asList(unDetallePrecio, unDetallePrecio);
	private static List<DetallePrecio> otrosDetallesPrecio = Arrays.asList(unDetallePrecio, otroDetallePrecio);
		
	//Presupuesto
	private static Presupuesto unPresupuesto = new Presupuesto(null, null, 1782, unosDetallesPrecio);
	private static Presupuesto otroPresupuesto = new Presupuesto(null, null, 1723, otrosDetallesPrecio);
	private static List<Presupuesto> listaPresupuestosVacia;
	private static List<Presupuesto> unaListaPresupuestos = Arrays.asList(unPresupuesto, unPresupuesto);
	private static List<Presupuesto> otraListaPresupuestos = Arrays.asList(otroPresupuesto, otroPresupuesto);
	private static List<Presupuesto> listaVariosPresupuestos = Arrays.asList(unPresupuesto, unPresupuesto, otroPresupuesto, otroPresupuesto);

	//Criterio Compra
	private static CriterioMenorPrecio criterioMenorPrecio = CriterioMenorPrecio.getInstance();
		
    public static Egreso buildEgresoSinPresupuestos(){
        return new Egreso(null, null, 542, null, null, null, 2, criterioMenorPrecio, listaPresupuestosVacia, null, UsuarioBuilder.getDosRevisores());
    }
    
    public static Egreso buildEgresoSinPresupuestosMinimos() {
    	return new Egreso(null, null, 123, null, null, null, 0, null, null, null, null);
    }
    
    public static Egreso buildEgresoCumplidor() {
    	return new Egreso(null, null, 87, unosDetallesOperacion, null, null, 2, criterioMenorPrecio, unaListaPresupuestos, null, UsuarioBuilder.getDosRevisores());
    }
    
    public static Egreso buildEgresoConDetallesDeDistintoTamanio() {
		return new Egreso(null, null, 475, unosDetallesOperacion, null, null, 2, null, listaVariosPresupuestos, null, null);
	}
    
    public static Egreso buildEgresoNoBasadoEnPresupuesto() {
		return new Egreso(null, null, 345, otrosDetallesOperacion, null, null, 2, null, otraListaPresupuestos, null, null);
	}
    
    public static Usuario getUnRevisor() {
    	return unRevisor;
    }
    
    public static Usuario getOtroRevisor() {
    	return otroRevisor;
    }
}

