package utn.dds.tpAnual.db.api.interfaces;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.api.apiMinisterio.dto.ProvinciasDTO;
import utn.dds.tpAnual.db.api.apiMinisterio.service.MinisterioAPIService;
import utn.dds.tpAnual.db.api.dto.PaisDTO;
import utn.dds.tpAnual.db.api.service.MercadoLibreAPIService;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MercadoLibreAPIService.class, MinisterioAPIService.class})
public class MostrarDiferenciasAPIServiceTest {

    @Autowired
    private MercadoLibreAPIService mercadoLibreAPIService;

    @Autowired
    private MinisterioAPIService ministerioAPIService;

    @Test
    public void mostrarDiferenciasPorConsola() {
        ProvinciasDTO provincia = ministerioAPIService.getProvincias();
        PaisDTO pais = mercadoLibreAPIService.getPaisDetail("AR");
        Gson gson = new Gson();
        if(provincia != null && pais != null){
            System.out.println("Mercadolibre devuelve: " + gson.toJson(pais));
            System.out.println("Ministerio devuelve: " + gson.toJson(provincia));
        }
    }

}