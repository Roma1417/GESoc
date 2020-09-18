package utn.dds.tpAnual.db.service.vinculacion.vinculador;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.EgresoBuilder;
import utn.dds.tpAnual.builders.EntidadJuridicaEmpresaBuilder;
import utn.dds.tpAnual.builders.IngresoBuilder;
import utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion.CriterioVinculacionFecha;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.entidad.EntidadJuridicaEmpresa;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.EgresoService;
import utn.dds.tpAnual.db.service.EntidadService;
import utn.dds.tpAnual.db.service.IngresoService;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@MockBean(ProgramadorDeTareas.class)
@DataJpaTest(showSql=false)
@DirtiesContext
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class VinculadorTest {

    @Autowired
    private Vinculador vinculador;

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private EgresoService egresoService;

    @Autowired
    private IngresoService ingresoService;

    @Before
    public void beforeEachTest(){
        entidadService.deleteAllInBatch();
        egresoService.deleteAllInBatch();
        ingresoService.deleteAllInBatch();
    }

    @Test
    public void vinculaEntidadSinOperacionesNiCriterioVinculacion(){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad").build();
        entidadService.save(entidad);
        vinculador.vincularEntidad(entidad);
        assertTrue(true);
    }

    @Test
    public void vincularEntidadSinOperacionesConCriterioVinculacion(){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad1").build();
        entidad.setCriterioVinculacion(CriterioVinculacionFecha.getInstance());
        entidadService.save(entidad);
        vinculador.vincularEntidad(entidad);
        assertTrue(true);
    }

    @Test
    public void vincularEgresosPorFecha(){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad2").build();
        Egreso egreso = new EgresoBuilder().buildOtroEgresoCompleto();
        egreso.setEntidadRealizadora(entidad);

        LocalDate yearAgo = LocalDate.now();
        yearAgo.minusYears(1l);
        Egreso egreso2 = new EgresoBuilder().buildEgresoCompletoConFecha(yearAgo);
        egreso2.setEntidadRealizadora(entidad);

        entidad.setCriterioVinculacion(CriterioVinculacionFecha.getInstance());
        egresoService.saveAll(Arrays.asList(egreso, egreso2));

        Ingreso ingreso = new IngresoBuilder().buildIngresoCompleto();
        ingresoService.save(ingreso);
        egresoService.findAll();
        vinculador.vincularEntidad(entidad);
        assertTrue(ingreso.getEgresosAsociados().contains(egreso));
    }


}
