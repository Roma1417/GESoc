package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.ActividadBuilder;
import utn.dds.tpAnual.builders.EgresoBuilder;
import utn.dds.tpAnual.builders.RequisitoSectorEmpresaBuilder;
import utn.dds.tpAnual.builders.SectorBuilder;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.afip.RequisitoSectorEmpresa;
import utn.dds.tpAnual.db.entity.afip.Sector;
import utn.dds.tpAnual.db.entity.afip.tamanios.MedianaTramo1;
import utn.dds.tpAnual.db.entity.afip.tamanios.MedianaTramo2;
import utn.dds.tpAnual.db.entity.afip.tamanios.Micro;
import utn.dds.tpAnual.db.entity.afip.tamanios.Pequenia;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EgresoServiceTest {

    @Autowired
    private EgresoService egresoService;
/*
    private RequisitoSectorEmpresa requisito1 = new RequisitoSectorEmpresaBuilder()
            .withMaximoEmpleados(100)
            .withMaximoFacturacion(200F)
            .withTamanioEmpresa(Micro.getInstance())
            .build();

    @Test
    public void persistenceTest() {
        Egreso egresoConDetallesDeDistintoTamanio = new EgresoBuilder().buildEgresoConDetallesDeDistintoTamanio();
        egresoService.save(egresoConDetallesDeDistintoTamanio);
        Egreso egreso = egresoService.getEgresoById(egresoConDetallesDeDistintoTamanio.getOperacionId());
        assertTrue(egreso.getCodigoOperacion() == egresoConDetallesDeDistintoTamanio.getCodigoOperacion());
    }*/

}