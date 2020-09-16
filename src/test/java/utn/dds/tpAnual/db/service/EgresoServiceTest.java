package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.EgresoBuilder;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EgresoServiceTest {

    @Autowired
    private EgresoService egresoService;

    @Test
    public void persistenceTest() {
        Egreso egreso = new EgresoBuilder().buildUnEgresoCompleto();
        egresoService.save(egreso);
        Egreso egresoObtenido = egresoService.getEgresoById(egreso.getOperacionId());
        assertTrue(egreso.getCodigoOperacion() == egresoObtenido.getCodigoOperacion());
    }

}