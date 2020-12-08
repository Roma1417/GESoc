package utn.dds.tpAnual.db.service.mongo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.mapping.TextScore;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.mongo.auditoria.TipoOperacion;
import utn.dds.tpAnual.db.mongo.entity.RegistroOperacion;
import utn.dds.tpAnual.db.service.jpaService.UsuarioService;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRegistroOperacionService {

    @Autowired
    private RegistroOperacionService registroOperacionService;

    @Autowired
    private UsuarioService usuarioService;

    @Before
    public void cleanDb(){
        registroOperacionService.deleteAll();
    }

    @Test
    public void persistenceTest() {
        Usuario usuario = new Usuario("Pepe", "Pepa", "123");
        registroOperacionService.registrarAlta(usuario);
        assertTrue(!registroOperacionService.findAll().isEmpty());
    }

    @Test
    public void persistirUsuarioRegistraAlta () {
        Usuario usuario = new Usuario("Usuario", "Usuario", "123");
        usuarioService.save(usuario);
        RegistroOperacion registroOperacion = registroOperacionService.findAll().get(0);
        assertTrue(registroOperacion.getTipoOperacion().equals(TipoOperacion.ALTA));
    }


    @Test
    public void eliminarUsuarioRegistraBaja () {
        Usuario usuario = new Usuario("Usuario2", "Usuario2", "123");
        usuarioService.save(usuario);
        usuarioService.delete(usuario);
        RegistroOperacion registroOperacion = registroOperacionService.findAll().get(1);
        assertTrue(registroOperacion.getTipoOperacion().equals(TipoOperacion.BAJA));
    }

    @Test
    public void modificarUsuarioRegistraModificacion () {
        Usuario usuario = new Usuario("Usuario3", "Usuario3", "123");
        usuarioService.save(usuario);
        usuario.setNombre("Nuevo nombre");
        usuarioService.save(usuario);
        RegistroOperacion registroOperacion = registroOperacionService.findAll().get(1);
        assertTrue(registroOperacion.getTipoOperacion().equals(TipoOperacion.MODIFICACION));
    }

}
