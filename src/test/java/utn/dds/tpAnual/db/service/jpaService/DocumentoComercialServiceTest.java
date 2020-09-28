package utn.dds.tpAnual.db.service.jpaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.transaccion.DocumentoComercial;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.DocumentoComercialService;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(ProgramadorDeTareas.class)
public class DocumentoComercialServiceTest {

    @Autowired
    private DocumentoComercialService documentoComercialService;

    DocumentoComercial unDocumento = new DocumentoComercial(123, 1, null, null);

    @Test
    public void persistenceTest() {
        documentoComercialService.save(unDocumento);
        DocumentoComercial otroDocumento = documentoComercialService.getFirstDocumentoComercialByNumero(123);
        assertTrue(unDocumento.getIdDocumento() == otroDocumento.getIdDocumento());
    }

}