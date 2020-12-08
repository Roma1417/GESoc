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
import utn.dds.tpAnual.db.dto.complex.VinculacionProyectoEgresoDTO;
import utn.dds.tpAnual.db.dto.complex.VinculacionProyectoIngresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.transaccion.ProyectoFinanciamientoDTO;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.entidad.EntidadJuridicaEmpresa;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.proveedor.ProveedorPersona;
import utn.dds.tpAnual.db.entity.transaccion.*;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.entity.usuario.Admin;
import utn.dds.tpAnual.db.entity.usuario.TipoUsuario;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.entity.usuario.UsuarioEntidad;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.*;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@MockBean(ProgramadorDeTareas.class)
@DataJpaTest(showSql=false)
@DirtiesContext
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ProyectoFinanciamientoResourceBeanTest {

    @Autowired
    private ProyectoFinanciamientoResourceBean proyectoFinanciamientoResourceBean;

    @Autowired
    private ProyectoFinanciamientoService proyectoFinanciamientoService;

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private EgresoService egresoService;

    @Test
    public void obtenerProyectosUsuarioRelacionadoDevuelve (){
        Entidad entidad = getTestEntidad("1");
        Usuario usuario = getTestUsuario(entidad);
        PageableRequest pageableRequest = new PageableRequest(usuario.getNombre(),1L, 5L);
        ProyectoFinanciamiento proyecto = new ProyectoFinanciamiento();
        proyecto.setEntidadRealizadora(entidad);
        proyecto.setDirector(usuario);
        proyectoFinanciamientoService.save(proyecto);
        PageableResponse<ProyectoFinanciamientoDTO, ProyectoFinanciamiento> proyectosEncontrados = proyectoFinanciamientoResourceBean.getProyectos(pageableRequest, usuario.getNombre());
        assertTrue(!proyectosEncontrados.getData().isEmpty());
    }

    @Test
    public void obtenerProyectosUsuarioNoRelacionadoVacio (){
        Entidad entidad = getTestEntidad("1");
        Usuario usuario = getTestUsuario(entidad);
        Entidad entidad2 = getTestEntidad("2");
        PageableRequest pageableRequest = new PageableRequest(usuario.getNombre(),1L, 5L);
        ProyectoFinanciamiento proyecto = new ProyectoFinanciamiento();
        proyecto.setEntidadRealizadora(entidad2);
        proyectoFinanciamientoService.save(proyecto);
        PageableResponse<ProyectoFinanciamientoDTO, ProyectoFinanciamiento> proyectosEncontrados = proyectoFinanciamientoResourceBean.getProyectos(pageableRequest, usuario.getNombre());
        assertTrue(proyectosEncontrados.getData().isEmpty());
    }

    @Test
    public void vinculacionProyectoIngresoValidosSuccess(){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad2").build();
        ProyectoFinanciamiento proyecto = new ProyectoFinanciamiento();
        Ingreso ingreso = getMockIngreso(entidad);

        proyectoFinanciamientoService.save(proyecto);
        ingresoService.save(ingreso);
        entidadService.save(entidad);

        VinculacionProyectoIngresoDTO vinculacion = new VinculacionProyectoIngresoDTO(proyecto.getProyectoId(),
                ingreso.getOperacionId());
        proyectoFinanciamientoResourceBean.vincularIngreso(vinculacion);
        assertTrue(proyecto.getIngresos().contains(ingreso));
    }

    @Test
    public void vinculacionProyectoEgresoValidosSuccess(){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre("Entidad2").build();
        Usuario usuario = getTestUsuario(entidad);
        ProyectoFinanciamiento proyecto = new ProyectoFinanciamiento(4000F, 1, null, null, usuario, entidad);
        Egreso egreso = getMockEgreso(entidad);

        proyectoFinanciamientoService.save(proyecto);
        egresoService.save(egreso);
        entidadService.save(entidad);

        VinculacionProyectoEgresoDTO vinculacion = new VinculacionProyectoEgresoDTO(proyecto.getProyectoId(),
                egreso.getOperacionId());
        proyectoFinanciamientoResourceBean.vincularEgreso(vinculacion);
        assertTrue(proyecto.getEgresos().contains(egreso));
    }

    private Entidad getTestEntidad(String name){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre(name).build();
        entidadService.save(entidad);
        return entidad;
    }

    private Usuario getTestUsuario(Entidad entidad){
        Usuario usuario = new Usuario("pepe", "pepe", "pepa");
        TipoUsuario tipoUsuario = new Admin();
        usuario.setUsuariosEntidad(Arrays.asList(new UsuarioEntidad(entidad, tipoUsuario, usuario)));
        usuarioService.save(usuario);
        return usuario;
    }

    private Ingreso getMockIngreso(Entidad entidad){
        return new IngresoBuilder()
                .withEntidadRealizadora(entidad)
                .withCodigoOperacion(2)
                .withTotal(100F).build();
    }

    private Egreso getMockEgreso(Entidad entidad){
        Pais pais = new Pais("Argentina");
        Moneda moneda = new Moneda("Patacones");
        MedioPago medioPago = new MedioPago("Tarjeta");
        Proveedor proveedor = new ProveedorPersona();
        return new EgresoBuilder()
                .withDetalleOperacion(new DetalleOperacionBuilder().mockDetalle().build())
                .withEntidadRealizadora(entidad)
                .withCodigoOperacion(1)
                .withDocumentoComercial(new DocumentoComercial(1, 1, pais, moneda))
                .withMedioPago(medioPago)
                .withProveedor(proveedor)
                .build();
    }
}
