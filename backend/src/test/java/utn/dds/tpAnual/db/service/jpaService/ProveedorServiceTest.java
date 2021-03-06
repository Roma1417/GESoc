package utn.dds.tpAnual.db.service.jpaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.proveedor.ProveedorPersona;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.ProveedorService;
import utn.dds.tpAnual.db.service.mongo.repository.RegistroOperacionRepository;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean({ProgramadorDeTareas.class, RegistroOperacionRepository.class })
public class ProveedorServiceTest {

    @Autowired
    private ProveedorService proveedorService;

    private DireccionPostal direccionPostal = new DireccionPostal("Calle falsa", 123111, 0, null);
    private Proveedor unProveedor = new ProveedorPersona(direccionPostal, "0","Frongi");

    @Test
    public void persistenceTest() {
        proveedorService.save(unProveedor);
        Proveedor mismoProveedor = proveedorService.getFirstProveedorByDireccionPostal(direccionPostal);
        assertTrue(mismoProveedor.getProveedorId() == unProveedor.getProveedorId());
    }

}