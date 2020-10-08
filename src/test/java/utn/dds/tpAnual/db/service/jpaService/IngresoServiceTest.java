package utn.dds.tpAnual.db.service.jpaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.IngresoBuilder;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.IngresoService;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(ProgramadorDeTareas.class)
public class IngresoServiceTest {

    @Autowired
    private IngresoService ingresoService;

    @Test
    public void persistenceTest() {
        Ingreso ingreso = new IngresoBuilder().buildIngresoCompleto();
        ingresoService.save(ingreso);
        Ingreso ingresoObtenido = ingresoService.getPrimerIngresoByCodigoOperacion(ingreso.getCodigoOperacion());
        assertTrue(ingreso.getCodigoOperacion().equals(ingresoObtenido.getCodigoOperacion()));
    }

}