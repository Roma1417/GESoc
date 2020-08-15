package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.afip.tamanios.Pequenia;
import utn.dds.tpAnual.db.entity.afip.tamanios.TamanioEmpresa;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TamanioEmpresaServiceTest {

    @Autowired
    private TamanioEmpresaService tamanioEmpresaService;

    private Pequenia unTamanioEmpresa = Pequenia.getInstance();

    @Test
    public void persistenceTest() {
        tamanioEmpresaService.save(unTamanioEmpresa);
        TamanioEmpresa mismoTamanio = tamanioEmpresaService.getTamanioSameNombre("pequenia");
        assertTrue(mismoTamanio.getNombre() == unTamanioEmpresa.getNombre());
    }

}