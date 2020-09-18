package utn.dds.tpAnual.db.api.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.ActividadBuilder;
import utn.dds.tpAnual.builders.RequisitoSectorEmpresaBuilder;
import utn.dds.tpAnual.builders.SectorBuilder;
import utn.dds.tpAnual.db.api.dto.PaisDTO;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.afip.RequisitoSectorEmpresa;
import utn.dds.tpAnual.db.entity.afip.Sector;
import utn.dds.tpAnual.db.entity.afip.tamanios.MedianaTramo1;
import utn.dds.tpAnual.db.entity.afip.tamanios.MedianaTramo2;
import utn.dds.tpAnual.db.entity.afip.tamanios.Micro;
import utn.dds.tpAnual.db.entity.afip.tamanios.Pequenia;
import utn.dds.tpAnual.db.service.ActividadService;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MercadoLibreAPIService.class)
public class MercadoLibreAPIServiceTest {

    @Autowired
    private MercadoLibreAPIService mercadoLibreAPIService;


    @Test
    public void devuelveAlgo() {
        List<PaisDTO> paises = mercadoLibreAPIService.getPaises();
        assertTrue(!paises.isEmpty());
    }

    @Test
    public void tieneArgentina() {
        List<PaisDTO> paises = mercadoLibreAPIService.getPaises();
        assertTrue(paises.stream().anyMatch(paisDTO -> "Argentina".equals(paisDTO.getName())));
    }

    @Test
    public void devuelveProvinciasArgentinas() {
        PaisDTO pais = mercadoLibreAPIService.getPaisDetail("AR");
        assertTrue(!pais.getStates().isEmpty());
    }

}