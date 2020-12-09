package utn.dds.tpAnual.db.service.jpaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.ImportInformacionGeograficaService;
import utn.dds.tpAnual.db.service.jpaService.CiudadService;
import utn.dds.tpAnual.db.service.jpaService.DireccionPostalService;
import utn.dds.tpAnual.db.service.mongo.repository.RegistroOperacionRepository;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean({ProgramadorDeTareas.class, RegistroOperacionRepository.class })
public class DireccionPostalServiceTest {

    @Autowired
    ImportInformacionGeograficaService importInformacionGeograficaService;

    @Autowired
    CiudadService ciudadService;

    @Autowired
    DireccionPostalService direccionPostalService;


    @Test
    public void persistenceTest(){
    DireccionPostal unaDireccionPostal = new DireccionPostal("Ventura de la vega", 2267, 0, null);
    direccionPostalService.save(unaDireccionPostal);
    DireccionPostal otraDireccionPostal = direccionPostalService.getFirstDireccionByCalle("Ventura de la vega");
    assertTrue(unaDireccionPostal.getCalle().equals(otraDireccionPostal.getCalle()));
    }
}
