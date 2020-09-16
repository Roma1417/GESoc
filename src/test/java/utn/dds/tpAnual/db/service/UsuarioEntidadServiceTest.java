package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.UsuarioBuilder;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.entity.usuario.UsuarioEntidad;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
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