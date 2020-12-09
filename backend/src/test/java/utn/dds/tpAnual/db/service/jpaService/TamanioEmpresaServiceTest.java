package utn.dds.tpAnual.db.service.jpaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.afip.tamanios.Pequenia;
import utn.dds.tpAnual.db.entity.afip.tamanios.TamanioEmpresa;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.TamanioEmpresaService;
import utn.dds.tpAnual.db.service.mongo.repository.RegistroOperacionRepository;


import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean({ProgramadorDeTareas.class, RegistroOperacionRepository.class })
public class TamanioEmpresaServiceTest {

    @Autowired
    private TamanioEmpresaService tamanioEmpresaService;

    private Pequenia unTamanioEmpresa = Pequenia.getInstance();

    @Test
    public void persistenceTest() {
        tamanioEmpresaService.save(unTamanioEmpresa);
        TamanioEmpresa mismoTamanio = tamanioEmpresaService.getTamanioSameNombre("Pequenia");
        assertTrue(mismoTamanio.getNombre().equals(unTamanioEmpresa.getNombre()));
    }

}