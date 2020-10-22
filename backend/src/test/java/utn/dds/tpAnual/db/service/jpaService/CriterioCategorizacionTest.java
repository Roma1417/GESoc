package utn.dds.tpAnual.db.service.jpaService;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion.CriterioCategorizacion;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(ProgramadorDeTareas.class)
public class CriterioCategorizacionTest {

	@Autowired
    private CriterioCategorizacionService criterioCategorizacionService;

	@Test
	public void persistenceTest() {
		CriterioCategorizacion criterio = new CriterioCategorizacion("Un criterio");
		Categoria categoria = new Categoria("Una categoria");
		criterio.addCategoria(categoria);
		criterioCategorizacionService.save(criterio);
		CriterioCategorizacion criterioObtenido = criterioCategorizacionService.getCriterioCategorizacionByDescripcion(criterio.getDescripcion());
		assertTrue(criterio.getDescripcion().equals(criterioObtenido.getDescripcion()));
	}
}
