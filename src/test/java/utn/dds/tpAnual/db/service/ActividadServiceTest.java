package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(ProgramadorDeTareas.class)
public class ActividadServiceTest {

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private SectorService sectorService;

    private RequisitoSectorEmpresa requisito1 = new RequisitoSectorEmpresaBuilder()
            .withMaximoEmpleados(100)
            .withMaximoFacturacion(200F)
            .withTamanioEmpresa(Micro.getInstance())
            .build();
    private RequisitoSectorEmpresa requisito2 = new RequisitoSectorEmpresaBuilder()
            .withMaximoEmpleados(500)
            .withMaximoFacturacion(800F)
            .withTamanioEmpresa(Pequenia.getInstance())
            .build();
    private RequisitoSectorEmpresa requisito3 = new RequisitoSectorEmpresaBuilder()
            .withMaximoEmpleados(10000)
            .withMaximoFacturacion(800F)
            .withTamanioEmpresa(MedianaTramo1.getInstance())
            .build();
    private RequisitoSectorEmpresa requisito4 = new RequisitoSectorEmpresaBuilder()
            .withMaximoEmpleados(100000)
            .withMaximoFacturacion(8000F)
            .withTamanioEmpresa(MedianaTramo2.getInstance())
            .build();

    private Sector sectorConRequisitosDesordenados = new SectorBuilder()
            .withNombre("Financiero")
            .withRequisitoSectorEmpresa(requisito4)
            .withRequisitoSectorEmpresa(requisito3)
            .withRequisitoSectorEmpresa(requisito1)
            .withRequisitoSectorEmpresa(requisito2).build();

    private Actividad actividad = new ActividadBuilder()
            .withNombre("Ganaderia")
            .build();

    @Test
    public void persistenceTest() {
        requisito1.setSector(sectorConRequisitosDesordenados);
        requisito2.setSector(sectorConRequisitosDesordenados);
        requisito3.setSector(sectorConRequisitosDesordenados);
        requisito4.setSector(sectorConRequisitosDesordenados);
        actividad.setSector(sectorConRequisitosDesordenados);
        actividadService.save(actividad);
        Actividad mismaActividad = actividadService.getPrimeraActividadByNombre("Ganaderia");
        assertTrue(mismaActividad.getActividadId() == actividad.getActividadId());
    }

}