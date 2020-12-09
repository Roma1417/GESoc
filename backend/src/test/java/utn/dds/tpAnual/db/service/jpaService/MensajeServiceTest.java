package utn.dds.tpAnual.db.service.jpaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.MensajeService;
import utn.dds.tpAnual.db.service.mongo.repository.RegistroOperacionRepository;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean({ProgramadorDeTareas.class, RegistroOperacionRepository.class })
public class MensajeServiceTest {

    @Autowired
    private MensajeService mensajeService;

    Mensaje unMensaje = new Mensaje("Compra", "Esto es un cuerpo de mensaje");

    @Test
    public void persistenceTest() {
        mensajeService.save(unMensaje);
        Mensaje otroMensaje = mensajeService.getFirstMensajeByAsunto("Compra");
        assertTrue(unMensaje.getMensajeId() == otroMensaje.getMensajeId());
    }

}