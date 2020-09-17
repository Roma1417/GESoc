package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import utn.dds.tpAnual.db.entity.ubicacion.Ciudad;
import utn.dds.tpAnual.db.entity.ubicacion.Estado;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;


import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=false")
public class ImportInformacionGeograficaServiceTest {

    @Autowired
    private ImportInformacionGeograficaService importInformacionGeograficaService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CiudadService ciudadService;

    @Autowired
    private MonedaService monedaService;

    @Test
    public void importaArgentina(){
        importInformacionGeograficaService.importPaises();
        Pais pais = paisService.getPrimerPaisByNombre("Argentina");
        assertTrue(pais != null);
    }

    @Test
    public void importaPaisesSinRepetir(){
        importInformacionGeograficaService.importPaises();
        importInformacionGeograficaService.importPaises();
        List<Pais> paises = paisService.getPaisesByNombre("Argentina");
        assertTrue(paises.size() == 1);
    }

    @Test
    public void importaCatamarca(){
        importInformacionGeograficaService.importPaises();
        importInformacionGeograficaService.importEstados();
        Estado estado = estadoService.getPrimerEstadoByIdAPI("TUxBUENBVGFiY2Fm");
        assertTrue(estado != null);
    }

    @Test
    public void importaEstadosSinRepetir(){
        importInformacionGeograficaService.importEstados();
        importInformacionGeograficaService.importEstados();
        List<Estado> estados = estadoService.getEstadosByIdAPI("TUxBUENBVGFiY2Fm");
        assertTrue(estados.size() == 1);
    }
/*
    @Test
    public void importaAvellaneda(){
        importInformacionGeograficaService.importPaises();
        importInformacionGeograficaService.importEstados();
        importInformacionGeograficaService.importCiudades();
        Ciudad ciudad = ciudadService.getPrimeraCiudadByIdAPI("TUxBQ0FWRTc5OTQ1");
        assertTrue(ciudad != null);  // o.h.engine.jdbc.spi.SqlExceptionHelper   : Deadlock found when trying to get lock; try restarting transaction
    }    */
/*
    @Test
    public void importaCiudadesSinRepetir(){
        importInformacionGeograficaService.importCiudades();
        importInformacionGeograficaService.importCiudades();
        List<Ciudad> ciudades = ciudadService.getCiudadesByIdAPI("TUxBQ0FWRTc5OTQ1");
        assertTrue(ciudades.size() == 1);
    }   TARDA 5 MINUTOS */

    @Test
    public void importaPesoArgentino(){
        importInformacionGeograficaService.importMonedas();
        Moneda moneda = monedaService.getPrimerMonedaByIdAPI("ARS");
        assertTrue(moneda != null);
    }

    @Test
    public void importaMonedasSinRepetir(){
        importInformacionGeograficaService.importMonedas();
        importInformacionGeograficaService.importMonedas();
        List<Moneda> monedas = monedaService.getMonedasByIdAPI("ARS");
        assertTrue(monedas.size() == 1);
    }

}