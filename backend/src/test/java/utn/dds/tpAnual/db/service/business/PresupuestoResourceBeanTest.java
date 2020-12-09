package utn.dds.tpAnual.db.service.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.*;
import utn.dds.tpAnual.db.dto.complex.VinculacionEgresoIngresoDTO;
import utn.dds.tpAnual.db.dto.entidad.EntidadDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.proveedor.ProveedorDTO;
import utn.dds.tpAnual.db.dto.transaccion.*;
import utn.dds.tpAnual.db.dto.ubicacion.MonedaDTO;
import utn.dds.tpAnual.db.dto.ubicacion.PaisDTO;
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
import utn.dds.tpAnual.db.service.mongo.repository.RegistroOperacionRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@MockBean({ProgramadorDeTareas.class, RegistroOperacionRepository.class })
@DataJpaTest(showSql=false)
@DirtiesContext
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PresupuestoResourceBeanTest {

    @Autowired
    private PresupuestoResourceBean presupuestoResourceBean;

    @Autowired
    private PresupuestoService presupuestoService;

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private MonedaService monedaService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private DetalleOperacionService detalleOperacionService;

    @Autowired
    private DetallePrecioService detallePrecioService;

    @Autowired
    private DocumentoComercialService documentoComercialService;

    @Autowired
    private EgresoService egresoService;


    @Test
    public void getPresupuestosControllerSinFiltrarCategoriaSuccess (){
        Entidad entidad = getTestEntidad("1");
        Usuario usuario = getTestUsuario(entidad);
        Presupuesto presupuesto = new PresupuestoBuilder().buildPresupuestoConDetallesEItems();
        presupuesto.setEntidadRealizadora(entidad);
        presupuestoService.save(presupuesto);

        PageableRequest pageableRequest = new PageableRequest(usuario.getUsuario(), 1L, 20L);
        PageableResponse<PresupuestoDTO, Presupuesto> presupuestos = presupuestoResourceBean.getPresupuestos(pageableRequest, null);
        PresupuestoDTO presupuestoDTO = presupuestos.getData().get(0);
        assertTrue(sonElMismoPresupuesto(presupuesto, presupuestoDTO));
    }

    @Test
    public void getPresupuestosControllerFiltrandoPorCategoriaSuccess (){
        Entidad entidad = getTestEntidad("1");
        Usuario usuario = getTestUsuario(entidad);
        Presupuesto presupuesto = new PresupuestoBuilder().buildPresupuestoConDetallesEItems();
        presupuesto.setEntidadRealizadora(entidad);
        presupuestoService.save(presupuesto);

        PageableRequest pageableRequest = new PageableRequest(usuario.getUsuario(), 1L, 20L);
        PageableResponse<PresupuestoDTO, Presupuesto> presupuestos = presupuestoResourceBean.getPresupuestos(pageableRequest, "Categoria por nombre");
        PresupuestoDTO presupuestoDTO = presupuestos.getData().get(0);
        assertTrue(sonElMismoPresupuesto(presupuesto, presupuestoDTO));
    }

    @Test
    public void crearPresupuestoValidandoDesdeDTO (){
        Entidad entidad = getTestEntidad("1");
        Usuario usuario = getTestUsuario(entidad);
        Egreso egreso = new EgresoBuilder().buildEgresoCompletoConFechaSinPresupuestos(LocalDate.now());
        egreso.setEntidadRealizadora(entidad);
        egresoService.save(egreso);

        Optional<Egreso> egresoObtenido = egresoService.findFullById(egreso.getOperacionId());
        Moneda moneda = egreso.getDocumentoComercial().getMoneda();
        Pais pais = egreso.getDocumentoComercial().getPais();
        PresupuestoDTO presupuesto = new PresupuestoDTOBuilder().buildPresupuestoDTOSinDetallesEItems();
        cargarPresupuestoDto(presupuesto, egresoObtenido.get().getOperacionId(), entidad,egreso, moneda, pais);
        PresupuestoDTO presupuestoDevuelto = presupuestoResourceBean.crearPresupuesto(presupuesto, usuario.getUsuario());

        assertTrue(sonElMismoPresupuestoDTO(presupuesto, presupuestoDevuelto));
    }

    private void cargarPresupuestoDto(PresupuestoDTO presupuesto, Long operacionId, Entidad entidad, Egreso egreso,
                                      Moneda moneda, Pais pais) {
        presupuesto.setEgresoID(operacionId);
        presupuesto.setEntidadRealizadora(new EntidadDTO().from(entidad));
        presupuesto.getDocumentoComercial().setMoneda(new MonedaDTO().from(moneda));
        presupuesto.getDocumentoComercial().setPais(new PaisDTO().from(pais));

        ItemDTO item = new ItemDTO().from(egreso.getDetallesOperacion().get(0).getItem());
        List<DetallePrecioDTO> detalles = Arrays.asList(
                new DetallePrecioDTO(200F, new DetalleOperacionDTO(item,200F,20,
                        egreso.getDetallesOperacion().get(0).getDetalleOperacionId())),
                new DetallePrecioDTO(100F, new DetalleOperacionDTO(item,100F,10,
                        egreso.getDetallesOperacion().get(1).getDetalleOperacionId())));
        presupuesto.setDetallesPrecio(detalles);
    }

    private Usuario getTestUsuario(Entidad entidad){
        Usuario usuario = new Usuario("pepe", "pepe", "pepa");
        TipoUsuario tipoUsuario = new Admin();
        usuario.setUsuariosEntidad(Arrays.asList(new UsuarioEntidad(entidad, tipoUsuario, usuario)));
        usuarioService.save(usuario);
        return usuario;
    }


    private Entidad getTestEntidad(String name){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre(name).build();
        entidadService.save(entidad);
        return entidad;
    }

    private boolean sonElMismoPresupuesto(Presupuesto presupuesto, PresupuestoDTO presupuestoDTO) {
        return presupuesto.getCodigoOperacion() == presupuestoDTO.getCodigoOperacion()
                && presupuesto.getTotal().equals(presupuestoDTO.getTotal())
                && presupuesto.getEntidadRealizadora().getNombre().equals(presupuestoDTO.getEntidadRealizadora().getNombre())
                && presupuesto.getDocumentoComercial().getNumero() == presupuestoDTO.getDocumentoComercial().getNumero()
                && presupuesto.getDetallesPrecio().size() == presupuestoDTO.getDetallesPrecio().size();
    }

    private boolean sonElMismoPresupuestoDTO(PresupuestoDTO presupuesto, PresupuestoDTO presupuestoDevuelto) {
        return presupuesto.getCodigoOperacion().equals(presupuestoDevuelto.getCodigoOperacion())
                && presupuesto.getTotal().equals(presupuestoDevuelto.getTotal())
                && presupuesto.getEntidadRealizadora().getNombre().equals(presupuestoDevuelto.getEntidadRealizadora().getNombre())
                && presupuesto.getDocumentoComercial().getNumero().equals(presupuestoDevuelto.getDocumentoComercial().getNumero())
                && presupuesto.getDetallesPrecio().size() == presupuestoDevuelto.getDetallesPrecio().size();
    }
}
