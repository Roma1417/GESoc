package utn.dds.tpAnual.db.api.apiMinisterio.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.api.apiMinisterio.dto.ProvinciasDTO;
import utn.dds.tpAnual.db.api.dto.PaisDTO;
import utn.dds.tpAnual.db.api.service.MercadoLibreAPIService;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MinisterioAPIService.class)
public class MinisterioAPIServiceTest {

    @Autowired
    private MinisterioAPIService ministerioAPIService;


    @Test
    public void devuelveAlgo() {
        ProvinciasDTO provincias = ministerioAPIService.getProvincias();
        assertTrue(!provincias.provincias.isEmpty());
    }

    @Test
    public void existeProvincia() {
        ProvinciasDTO provincia = ministerioAPIService.getProvincia("Cordoba");
        assertTrue(provincia != null);
    }

}