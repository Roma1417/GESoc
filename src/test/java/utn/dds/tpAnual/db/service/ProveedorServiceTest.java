package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.ActividadBuilder;
import utn.dds.tpAnual.builders.RequisitoSectorEmpresaBuilder;
import utn.dds.tpAnual.builders.SectorBuilder;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.afip.RequisitoSectorEmpresa;
import utn.dds.tpAnual.db.entity.afip.Sector;
import utn.dds.tpAnual.db.entity.afip.tamanios.MedianaTramo1;
import utn.dds.tpAnual.db.entity.afip.tamanios.MedianaTramo2;
import utn.dds.tpAnual.db.entity.afip.tamanios.Micro;
import utn.dds.tpAnual.db.entity.afip.tamanios.Pequenia;
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