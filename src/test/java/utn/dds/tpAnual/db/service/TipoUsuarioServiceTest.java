package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.usuario.Estandar;
import utn.dds.tpAnual.db.entity.usuario.TipoUsuario;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(ProgramadorDeTareas.class)
public class TipoUsuarioServiceTest {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    Estandar tipoEstandar = new Estandar();

    @Test
    public void persistenceTest() {
        tipoUsuarioService.save(tipoEstandar);
        TipoUsuario mismoTipoEstandar = tipoUsuarioService.getFirstTipoUsuarioByDescripcion("Usuario estándar");
        assertTrue(tipoEstandar.getTipoUsuarioId() == mismoTipoEstandar.getTipoUsuarioId());
    }

}