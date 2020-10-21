package utn.dds.tpAnual.db.service.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.UsuarioService;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@MockBean(ProgramadorDeTareas.class)
@DataJpaTest(showSql=false)
@DirtiesContext
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioResourceBeanTest {

    @Autowired
    private UsuarioResourceBean usuarioResourceBean;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void loginWithUsuarioValidoSuccess(){
        Usuario usuario = new Usuario("nombre","usuario", "pwd");
        usuarioService.save(usuario);
        usuarioResourceBean.login("usuario", "pwd");
        assertTrue(true);
    }

    @Test
    public void loginWithUsuarioInvalidoError(){
        Usuario usuario = new Usuario("nombre","usuario", "pwd");
        usuarioService.save(usuario);
        assertThrows(RuntimeException.class, () -> {
            usuarioResourceBean.login("usuario", "noEsPwd");
        });
    }

    @Test
    public void loginWithUsuarioInexistenteError(){
        Usuario usuario = new Usuario("nombre","usuario", "pwd");
        usuarioService.save(usuario);
        assertThrows(RuntimeException.class, () -> {
            usuarioResourceBean.login("noEsUsuario", "pwd");
        });
    }
}
