package utn.dds.tpAnual.db.service.mongo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.mapping.TextScore;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.mongo.auditoria.TipoOperacion;
import utn.dds.tpAnual.db.mongo.entity.RegistroOperacion;
import utn.dds.tpAnual.db.service.jpaService.UsuarioService;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistroOperacionServiceTest {

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
        registroOperacionService.registrarAlta(usuario, "usuario");
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

    @Test
    public void getDeAltaPorNombreClaseYTipoOperacionDevuelve () {
        Usuario usuario = new Usuario("UsuarioBuscado1", "UsuarioBuscado1", "123");
        usuarioService.save(usuario);
        Page<RegistroOperacion> registroOperacionPage = registroOperacionService
                .getRegistroOperacionByTipoAndNombreClase(new PageableRequest("Pepe",1l, 10l ), TipoOperacion.ALTA,
                        "Usuario");
        assertTrue(registroOperacionPage.getTotalElements() == 1l);
    }

    @Test
    public void getDeAltaPorNombreClaseDevuelve () {
        Usuario usuario = new Usuario("UsuarioBuscado2", "UsuarioBuscado2", "123");
        usuarioService.save(usuario);
        Page<RegistroOperacion> registroOperacionPage = registroOperacionService
                .getRegistroOperacionByTipoAndNombreClase(new PageableRequest("Pepe",1l, 10l ), null,
                        "Usuario");
        assertTrue(registroOperacionPage.getTotalElements() == 1l);
    }

    @Test
    public void getDeAltaPorTipoOperacionDevuelve () {
        Usuario usuario = new Usuario("UsuarioBuscado3", "UsuarioBuscado3", "123");
        usuarioService.save(usuario);
        Page<RegistroOperacion> registroOperacionPage = registroOperacionService
                .getRegistroOperacionByTipoAndNombreClase(new PageableRequest("Pepe",1l, 10l ), TipoOperacion.ALTA,
                        null);
        assertTrue(registroOperacionPage.getTotalElements() == 1l);
    }

    @Test
    public void getDeNombresEntidadDevuelve (){
        Usuario usuario = new Usuario("UsuarioBuscado", "UsuarioBuscado", "123");
        usuarioService.save(usuario);
        Collection<String> nombresClase = registroOperacionService.findAllNombresEntidad();
        assertTrue(nombresClase.size() == 1);
    }

}
