package utn.dds.tpAnual.db.service.jpaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.transaccion.ProyectoFinanciamiento;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(ProgramadorDeTareas.class)
public class ProyectoFinanciamientoServiceTest {

    @Autowired private ProyectoFinanciamientoService proyectoFinanciamientoService;

    @Test
    public void persistenceTest() {
        ProyectoFinanciamiento proyecto = new ProyectoFinanciamiento();
        proyectoFinanciamientoService.save(proyecto);
        ProyectoFinanciamiento proyectoObtenido = proyectoFinanciamientoService.getProyectoById(proyecto.getOperacionId());
        assertTrue(proyecto.getCodigoOperacion() == proyectoObtenido.getCodigoOperacion());
    }

}