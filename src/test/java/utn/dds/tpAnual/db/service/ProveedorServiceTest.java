package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.proveedor.ProveedorPersona;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProveedorServiceTest {

    @Autowired
    private ProveedorService proveedorService;

    private DireccionPostal direccionPostal = new DireccionPostal("Calle falsa", 123111, 0, null);
    private Proveedor unProveedor = new ProveedorPersona(direccionPostal, 0L,"Frongi");

    @Test
    public void persistenceTest() {
        proveedorService.save(unProveedor);
        Proveedor mismoProveedor = proveedorService.getFirstProveedorByDireccionPostal(direccionPostal);
        assertTrue(mismoProveedor.getProveedorId() == unProveedor.getProveedorId());
    }

}