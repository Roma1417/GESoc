package utn.dds.tpAnual.db.service.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.DetalleOperacionBuilder;
import utn.dds.tpAnual.builders.EgresoBuilder;
import utn.dds.tpAnual.builders.EntidadJuridicaEmpresaBuilder;
import utn.dds.tpAnual.builders.IngresoBuilder;
import utn.dds.tpAnual.db.dto.transaccion.EgresoDTO;
import utn.dds.tpAnual.db.dto.complex.VinculacionEgresoIngresoDTO;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.entidad.EntidadJuridicaEmpresa;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.EgresoService;
import utn.dds.tpAnual.db.service.jpaService.EntidadService;
import utn.dds.tpAnual.db.service.jpaService.IngresoService;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@MockBean(ProgramadorDeTareas.class)
@DataJpaTest(showSql=false)
@DirtiesContext
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class EgresoResourceBeanTest {

    @Autowired
    private EgresoResourceBean egresoResourceBean;

    @Autowired
    private EgresoService egresoService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private EntidadService entidadService;

    @Test
    public void crearEgresoSinDetallesFails (){
        EgresoDTO egresoDTO = new EgresoDTO();

        assertThrows(RuntimeException.class,() -> {
            egresoResourceBean.crearEgresos(egresoDTO);
        });

    }

    @Test
    public void vinculacionEgresoIngresoValidosSuccess(){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad2").build();
        Egreso egreso = getMockEgreso(entidad);
        Ingreso ingreso = getMockIngreso(entidad);

        egresoService.save(egreso);
        ingresoService.save(ingreso);
        entidadService.save(entidad);

        VinculacionEgresoIngresoDTO vinculacion = new VinculacionEgresoIngresoDTO(ingreso.getOperacionId(),
                egreso.getOperacionId());
        egresoResourceBean.vincular(vinculacion);
        assertTrue(ingreso.getEgresosAsociados().contains(egreso));
    }

    @Test
    public void vinculacionIngresoNoExistenteError(){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad2").build();
        Egreso egreso = getMockEgreso(entidad);

        egresoService.save(egreso);
        entidadService.save(entidad);

        VinculacionEgresoIngresoDTO vinculacion = new VinculacionEgresoIngresoDTO(10000L,
                egreso.getOperacionId());
        assertThrows(RuntimeException.class,() -> {
            egresoResourceBean.vincular(vinculacion);
        });
    }

    @Test
    public void vinculacionEgresoNoExistenteError(){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad2").build();
        Ingreso ingreso = getMockIngreso(entidad);

        ingresoService.save(ingreso);
        entidadService.save(entidad);

        VinculacionEgresoIngresoDTO vinculacion = new VinculacionEgresoIngresoDTO(ingreso.getOperacionId(),
                10000L);
        assertThrows(RuntimeException.class,() -> {
            egresoResourceBean.vincular(vinculacion);
        });
    }

    @Test
    public void vinculacionDistintaEntidadError(){
        EntidadJuridicaEmpresa entidad1 = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad1").build();
        EntidadJuridicaEmpresa entidad2 = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad2").build();
        Egreso egreso = getMockEgreso(entidad1);
        Ingreso ingreso = getMockIngreso(entidad2);

        egresoService.save(egreso);
        ingresoService.save(ingreso);
        entidadService.save(entidad1);
        entidadService.save(entidad2);

        VinculacionEgresoIngresoDTO vinculacion = new VinculacionEgresoIngresoDTO(ingreso.getOperacionId(),
                egreso.getOperacionId());
        assertThrows(RuntimeException.class,() -> {
            egresoResourceBean.vincular(vinculacion);
        });
    }

    @Test
    public void vinculacionIngresoMenorEgresoError(){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad1").build();
        Egreso egreso = getMockEgreso(entidad);
        Ingreso ingreso = getMockIngreso(entidad);
        ingreso.setTotal(0F);

        egresoService.save(egreso);
        ingresoService.save(ingreso);
        entidadService.save(entidad);

        VinculacionEgresoIngresoDTO vinculacion = new VinculacionEgresoIngresoDTO(ingreso.getOperacionId(),
                egreso.getOperacionId());
        assertThrows(RuntimeException.class,() -> {
            egresoResourceBean.vincular(vinculacion);
        });
    }

    @Test
    public void vinculacionYaVinculadoError(){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad1").build();
        Egreso egreso = getMockEgreso(entidad);
        Ingreso ingreso = getMockIngreso(entidad);

        egresoService.save(egreso);
        ingresoService.save(ingreso);
        entidadService.save(entidad);

        VinculacionEgresoIngresoDTO vinculacion = new VinculacionEgresoIngresoDTO(ingreso.getOperacionId(),
                egreso.getOperacionId());
        assertThrows(RuntimeException.class,() -> {
            egresoResourceBean.vincular(vinculacion);
            egresoResourceBean.vincular(vinculacion);
        });
    }


    private Ingreso getMockIngreso(Entidad entidad){
        return new IngresoBuilder()
                .withEntidadRealizadora(entidad)
                .withCodigoOperacion(2)
                .withTotal(100F).build();
    }

    private Egreso getMockEgreso(Entidad entidad){
        return new EgresoBuilder()
                .withDetalleOperacion(new DetalleOperacionBuilder().mockDetalle().build())
                .withEntidadRealizadora(entidad)
                .withCodigoOperacion(1)
                .build();
    }


}
