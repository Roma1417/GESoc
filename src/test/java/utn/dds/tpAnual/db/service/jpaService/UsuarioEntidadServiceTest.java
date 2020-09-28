package utn.dds.tpAnual.db.service.jpaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.usuario.UsuarioEntidad;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.UsuarioEntidadService;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(ProgramadorDeTareas.class)
public class UsuarioEntidadServiceTest {

    @Autowired
    private UsuarioEntidadService usuarioEntidadService;

    UsuarioEntidad unUsuarioEntidad = new UsuarioEntidad();

    @Test
    public void persistenceTest() {
        usuarioEntidadService.save(unUsuarioEntidad);
        UsuarioEntidad otroUsuarioEntidad = usuarioEntidadService.getFirstUsuarioEntidadById(unUsuarioEntidad.getUsuarioEntidadId());
        assertTrue(unUsuarioEntidad.getUsuarioEntidadId().equals(otroUsuarioEntidad.getUsuarioEntidadId()));
    }

}