package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DireccionPostalServiceTest {

    @Autowired
    ImportInformacionGeograficaService importInformacionGeograficaService;

    @Autowired
    CiudadService ciudadService;

    @Autowired
    DireccionPostalService direccionPostalService;


    /*@Test
    public void persistenceTest(){
    importInformacionGeograficaService.importPaises();
    importInformacionGeograficaService.importEstados();
    importInformacionGeograficaService.importCiudades();
    DireccionPostal direccionPostal = new DireccionPostal("Ventura de la vega", 2267, 0, ciudadService.getPrimeraCiudadByIdAPI("'TUNPQ0lUQThkMzc5'"));
    direccionPostalService.save(direccionPostal);
    List<DireccionPostal> direcciones = direccionPostalService.findAll();
    assertTrue(direcciones.isEmpty());
    }*/
}
