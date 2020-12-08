package utn.dds.tpAnual.db.service.mongo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.mapping.TextScore;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.service.jpaService.UsuarioService;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRegistroOperacionService {

    @Autowired
    private RegistroOperacionService registroOperacionService;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void persistenceTest() {
        Usuario usuario = new Usuario("Pepe", "Pepa", "123");
        registroOperacionService.registrarAlta(usuario);
        assertTrue(!registroOperacionService.findAll().isEmpty());
    }

    @Test
    public void persistirUsuarioRegistraBien () {
        Usuario usuario = new Usuario("Usuario", "Usuario", "123");
        usuarioService.save(usuario);
        assertTrue(!registroOperacionService.findAll().isEmpty());

    }

}
