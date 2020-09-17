package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.PresupuestoBuilder;
import utn.dds.tpAnual.db.entity.transaccion.Presupuesto;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(ProgramadorDeTareas.class)
public class PresupuestoServiceTest {

    @Autowired
    private PresupuestoService presupuestoService;

    @Test
    public void persistenceTest() {
        Presupuesto presupuesto = new PresupuestoBuilder().buildPresupuestoConDetallesEItems();
        presupuestoService.save(presupuesto);
        Presupuesto presupuestoObtenido = presupuestoService.getPresupuestoById(presupuesto.getOperacionId());
        assertTrue(presupuestoObtenido.getCodigoOperacion() == presupuestoObtenido.getCodigoOperacion());
    }

}