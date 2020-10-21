package utn.dds.tpAnual.db.api.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.api.dto.PaisDTO;

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