package utn.dds.tpAnual.db.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.CategoriaNombreCorto;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.CategoriaNombreLargo;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion.CriterioCategorizacion;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion.CriterioCategorizacionPorDescripcion;
import utn.dds.tpAnual.db.entity.transaccion.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriterioCategorizacionTest {

	@Autowired
	CriterioCategorizacionService criterioCategorizacionService;

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

	@Test
	public void persistenceTest() {
		CriterioCategorizacion criterio = CriterioCategorizacionPorDescripcion.getInstance();
		criterioCategorizacionService.save(criterio);
		CriterioCategorizacion criterioObtenido = criterioCategorizacionService.getCriterioCategorizacionByDescripcion(criterio.getDescripcion());
		assertTrue(criterio.getDescripcion().equals(criterioObtenido.getDescripcion()));
	}
}
