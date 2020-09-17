package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.UsuarioBuilder;
import utn.dds.tpAnual.db.entity.usuario.Estandar;
import utn.dds.tpAnual.db.entity.usuario.TipoUsuario;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.service.TipoUsuarioService;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    Usuario unUsuario = new UsuarioBuilder().
            withContrasenia("contraseña").
            withNombre("Roma")
            .build();

    @Test
    public void persistenceTest() {
        usuarioService.save(unUsuario);
        Usuario otroUsuario = usuarioService.getFirstUsuarioByNombre("Roma");
        assertTrue(unUsuario.getUsuarioId().equals(otroUsuario.getUsuarioId()));
    }

}