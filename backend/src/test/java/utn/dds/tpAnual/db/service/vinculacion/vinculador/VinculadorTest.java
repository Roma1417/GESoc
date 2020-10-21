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
import utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion.*;
import utn.dds.tpAnual.db.entity.entidad.EntidadJuridicaEmpresa;
import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.CriterioVinculacionService;
import utn.dds.tpAnual.db.service.jpaService.EgresoService;
import utn.dds.tpAnual.db.service.jpaService.EntidadService;
import utn.dds.tpAnual.db.service.jpaService.IngresoService;

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

    @Autowired
    private CriterioVinculacionService criterioVinculacionService;

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
        entidad.setCriterioVinculacion(criterioVinculacionService.getFecha());
        entidadService.save(entidad);
        vinculador.vincularEntidad(entidad);
        assertTrue(true);
    }

    @Test
    public void vincularEgresosPorFecha(){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad2").build();
        entidadService.save(entidad);
        entidad.setCriterioVinculacion(criterioVinculacionService.getFecha());
        Egreso egreso = new EgresoBuilder().buildOtroEgresoCompleto();
        egreso.setEntidadRealizadora(entidad);
        Egreso egreso2 = new EgresoBuilder().buildEgresoCompletoConFecha(LocalDate.now().minusYears(1l));
        egreso2.setEntidadRealizadora(entidad);

        egresoService.saveAll(Arrays.asList(egreso, egreso2));

        Ingreso ingreso = new IngresoBuilder().buildIngresoCompleto();
        ingreso.setEntidadRealizadora(entidad);
        ingresoService.save(ingreso);
        vinculador.vincularEntidad(entidad);
        assertTrue(ingreso.getEgresosAsociados().contains(egreso));
    }

    @Test
    public void vincularEgresoPorOrdenValorPrimerEgreso(){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad2").build();
        entidadService.save(entidad);
        Egreso egreso = new EgresoBuilder().buildOtroEgresoCompleto();
        egreso.setEntidadRealizadora(entidad);

        Egreso egreso2 = new EgresoBuilder().buildOtroEgresoCompleto();
        egreso2.addDetalleOperacion(new DetalleOperacion(null, 1000F, 2));
        egreso2.setEntidadRealizadora(entidad);

        entidad.setCriterioVinculacion(criterioVinculacionService.getValorPrimerEgreso());
        egresoService.saveAll(Arrays.asList(egreso, egreso2));

        Ingreso ingreso = new IngresoBuilder().buildIngresoCompleto();
        ingreso.setEntidadRealizadora(entidad);

        ingresoService.save(ingreso);
        vinculador.vincularEntidad(entidad);
        assertTrue(ingreso.getEgresosAsociados().contains(egreso));
    }

    @Test
    public void vincularEgresoPorOrdenValorPrimerIngreso(){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad2").build();
        entidadService.save(entidad);
        Egreso egreso = new EgresoBuilder().buildOtroEgresoCompleto();
        egreso.setEntidadRealizadora(entidad);

        Egreso egreso2 = new EgresoBuilder().buildOtroEgresoCompleto();
        egreso2.addDetalleOperacion(new DetalleOperacion(null, 1000F, 2));
        egreso2.setEntidadRealizadora(entidad);

        entidad.setCriterioVinculacion(criterioVinculacionService.getValorPrimerIngreso());
        egresoService.saveAll(Arrays.asList(egreso, egreso2));

        Ingreso ingreso = new IngresoBuilder().buildIngresoCompleto();
        ingreso.setEntidadRealizadora(entidad);
        Ingreso ingreso2 = new IngresoBuilder().buildIngresoCompleto();
        ingreso2.setFecha(LocalDate.now().minusYears(2L));
        ingreso2.setTotal(5000F);
        ingreso.setEntidadRealizadora(entidad);

        ingresoService.save(ingreso);
        ingresoService.save(ingreso2);
        vinculador.vincularEntidad(entidad);
        assertTrue(ingreso.getEgresosAsociados().contains(egreso) &&
                ingreso2.getEgresosAsociados().isEmpty());
    }

    @Test
    public void vincularPorCriterioVinculacionMix(){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad2").build();
        CriterioVinculacionMix criterioVinculacion = new CriterioVinculacionMix();

        criterioVinculacion.addCriterioVinculacion(criterioVinculacionService.getValorPrimerIngreso());
        criterioVinculacion.addCriterioVinculacion(criterioVinculacionService.getFecha());
        entidad.setCriterioVinculacion(criterioVinculacion);

        entidadService.save(entidad);
        Egreso egreso = new EgresoBuilder().buildOtroEgresoCompleto();
        egreso.setEntidadRealizadora(entidad);

        Egreso egreso2 = new EgresoBuilder().buildOtroEgresoCompleto();
        egreso2.addDetalleOperacion(new DetalleOperacion(null, 1000F, 2));
        egreso2.setEntidadRealizadora(entidad);
        egresoService.saveAll(Arrays.asList(egreso, egreso2));

        Ingreso ingreso = new IngresoBuilder().buildIngresoCompleto();
        ingreso.setEntidadRealizadora(entidad);
        Ingreso ingreso2 = new IngresoBuilder().buildIngresoCompleto();
        ingreso2.setFecha(LocalDate.now().minusYears(2L));
        ingreso2.setTotal(5000F);
        ingreso.setEntidadRealizadora(entidad);

        ingresoService.saveAll(Arrays.asList(ingreso, ingreso2));
        vinculador.vincularEntidad(entidad);
        assertTrue(ingreso.getEgresosAsociados().contains(egreso) &&
                ingreso2.getEgresosAsociados().isEmpty());
    }



}
