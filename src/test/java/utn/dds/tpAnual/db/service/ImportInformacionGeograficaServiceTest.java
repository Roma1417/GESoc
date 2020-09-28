package utn.dds.tpAnual.db.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import utn.dds.tpAnual.db.api.dto.PaisDTO;
import utn.dds.tpAnual.db.api.service.MercadoLibreAPIService;
import utn.dds.tpAnual.db.entity.ubicacion.Ciudad;
import utn.dds.tpAnual.db.entity.ubicacion.Estado;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.CiudadService;
import utn.dds.tpAnual.db.service.jpaService.EstadoService;
import utn.dds.tpAnual.db.service.jpaService.MonedaService;
import utn.dds.tpAnual.db.service.jpaService.PaisService;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@MockBean(ProgramadorDeTareas.class)
@DataJpaTest(showSql=false)
@DirtiesContext
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ImportInformacionGeograficaServiceTest {

    @Autowired
    private ImportInformacionGeograficaService importInformacionGeograficaService;

    @Autowired
    private PaisService paisService;

    @MockBean
    private MercadoLibreAPIService mercadoLibreAPIService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CiudadService ciudadService;

    @Autowired
    private MonedaService monedaService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PaisDTO argentina = new PaisDTO();
        argentina.setId("AR");
        argentina.setName("Argentina");
        Mockito.when(mercadoLibreAPIService.getPaises()).thenReturn(Arrays.asList(argentina));
        Mockito.when(mercadoLibreAPIService.getMonedas()).thenCallRealMethod();
        Mockito.when(mercadoLibreAPIService.getCiudadDetail(any(String.class))).thenCallRealMethod();
        Mockito.when(mercadoLibreAPIService.getPaisDetail(any(String.class))).thenCallRealMethod();
        Mockito.when(mercadoLibreAPIService.getEstadoDetail(any(String.class))).thenCallRealMethod();
    }

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
        importInformacionGeograficaService.importPaises();
        importInformacionGeograficaService.importEstados();
        importInformacionGeograficaService.importEstados();
        List<Estado> estados = estadoService.getEstadosByIdAPI("TUxBUENBVGFiY2Fm");
        assertTrue(estados.size() == 1);
    }

    @Test
    public void importaAvellaneda(){
        importInformacionGeograficaService.importPaises();
        importInformacionGeograficaService.importEstados();
        importInformacionGeograficaService.importCiudades();
        Ciudad ciudad = ciudadService.getPrimeraCiudadByIdAPI("TUxBQ0FWRTc5OTQ1");
        assertTrue(ciudad != null);  // o.h.engine.jdbc.spi.SqlExceptionHelper   : Deadlock found when trying to get lock; try restarting transaction
    }

    @Test
    public void importaCiudadesSinRepetir(){
        importInformacionGeograficaService.importPaises();
        importInformacionGeograficaService.importEstados();
        importInformacionGeograficaService.importCiudades();
        List<Ciudad> ciudades = ciudadService.getCiudadesByIdAPI("TUxBQ0FWRTc5OTQ1");
        assertTrue(ciudades.size() == 1);
    }

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