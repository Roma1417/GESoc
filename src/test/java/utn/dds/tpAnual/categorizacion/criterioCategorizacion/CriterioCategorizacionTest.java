package utn.dds.tpAnual.categorizacion.criterioCategorizacion;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utn.dds.tpAnual.categorizacion.categoria.CategoriaNombreCorto;
import utn.dds.tpAnual.categorizacion.categoria.CategoriaNombreLargo;
import utn.dds.tpAnual.transaccion.Item;



public class CriterioCategorizacionTest {	

	@Test
	public void testCategorizacionSimple() {
		Item item = new Item(0L,"Descripcion pepe", CriterioCategorizacionPorDescripcion.getInstance());
		item.recategorizar();
		assertTrue(item.getCategoria().equals(CategoriaNombreCorto.getInstance()));
	}
	
	@Test
	public void testCategorizacionLarga() {
		Item item = new Item(0L,"Descripcion pepe muy largaaaaaa", CriterioCategorizacionPorDescripcion.getInstance());
		item.recategorizar();
		assertTrue(item.getCategoria().equals(CategoriaNombreLargo.getInstance()));
	}
}
