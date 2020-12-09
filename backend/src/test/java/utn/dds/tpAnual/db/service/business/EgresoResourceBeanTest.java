package utn.dds.tpAnual.db.service.business;

import org.apache.tomcat.jni.Local;
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
import utn.dds.tpAnual.db.dto.entidad.EntidadDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.proveedor.ProveedorDTO;
import utn.dds.tpAnual.db.dto.transaccion.*;
import utn.dds.tpAnual.db.dto.complex.VinculacionEgresoIngresoDTO;
import utn.dds.tpAnual.db.dto.ubicacion.MonedaDTO;
import utn.dds.tpAnual.db.dto.ubicacion.PaisDTO;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.entidad.EntidadBase;
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
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@MockBean({ProgramadorDeTareas.class, RegistroOperacionRepository.class })
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


    @Test
    public void crearEgresoSinDetallesError (){
        Entidad entidad = getTestEntidad("1");
        Usuario usuario = getTestUsuario(entidad);
        EgresoDTO egresoDTO = new EgresoDTO();

        assertThrows(RuntimeException.class,() -> {
            egresoResourceBean.crearEgreso(egresoDTO, usuario.getUsuario());
        });
    }

    @Test
    public void crearEgresoCompletoSuccess (){
        Entidad entidad = getTestEntidad("1");
        EntidadDTO entidadDTO = new EntidadDTO();
        Usuario usuario = getTestUsuario(entidad);
        EgresoDTO egresoDTO = new EgresoDTO();

        entidadDTO.setIdEntidad(entidad.getEntidadId());

        egresoDTO.setCantidadPresupuestosMinimos(1);
        egresoDTO.setCodigoOperacion(1);
        egresoDTO.setFechaOperacion(LocalDate.now());
        egresoDTO.setPresupuestos(null);
        egresoDTO.setProveedor(getTestProveedor());
        egresoDTO.setDetalles(Arrays.asList(getTestDetalle()));
        egresoDTO.setDocumentoComercial(getTestDocumentoComercial());
        egresoDTO.setEntidadRealizadora(entidadDTO);
        egresoDTO.setMedioPago(getTestMedioPago());

        egresoResourceBean.crearEgreso(egresoDTO, usuario.getUsuario());
        assertTrue(true);
    }

    @Test
    public void getEgresosSinFiltrarCategoriaSuccess (){
        Entidad entidad = getTestEntidad("1");
        Usuario usuario = getTestUsuario(entidad);
        Egreso egreso = new EgresoBuilder().buildEgresoCompletoConFecha(LocalDate.now());
        egreso.setEntidadRealizadora(entidad);
        egresoService.save(egreso);

        PageableRequest pageableRequest = new PageableRequest(usuario.getUsuario(), 1L, 20L);
        PageableResponse<EgresoDTO, Egreso> egresos = egresoResourceBean.getEgresos(pageableRequest, null);
        EgresoDTO egresoDto = egresos.getData().get(0);
        assertTrue(sonElMismoEgreso(egreso, egresoDto));
    }

    @Test
    public void getEgresosFiltrandoCategoriaSuccess (){
        Entidad entidad = getTestEntidad("Un nombre");
        Usuario usuario = getTestUsuario(entidad);
        Egreso egreso = new EgresoBuilder().buildEgresoCompletoConFecha(LocalDate.now());
        Categoria categoria = new Categoria("Una categoria");
        egreso.getDetallesOperacion().get(0).getItem().setCategoria(categoria);
        egreso.setEntidadRealizadora(entidad);
        egresoService.save(egreso);

        PageableRequest pageableRequest = new PageableRequest(usuario.getUsuario(), 1L, 20L);
        PageableResponse<EgresoDTO, Egreso> egresos = egresoResourceBean.getEgresos(pageableRequest, categoria.getIdCategoria().toString());
        EgresoDTO egresoDto = egresos.getData().get(0);
        assertTrue(sonElMismoEgreso(egreso, egresoDto));
    }

    private boolean sonElMismoEgreso(Egreso egreso, EgresoDTO egresoDto) {
        return egreso.getCodigoOperacion() == egresoDto.getCodigoOperacion()
                && egreso.getCantidadPresupuestosMinimos() == egresoDto.getCantidadPresupuestosMinimos()
                && egreso.getEntidadRealizadora().getNombre().equals(egresoDto.getEntidadRealizadora().getNombre())
                && egreso.getDocumentoComercial().getNumero() == egresoDto.getDocumentoComercial().getNumero()
                && egreso.getDetallesOperacion().size() == egresoDto.getDetalles().size();
    }


    private MedioPagoDTO getTestMedioPago(){
        MedioPago medioPago = new MedioPago();
        medioPago.setInstrumentoPago("Tarjeta");
        medioPagoService.save(medioPago);
        MedioPagoDTO medioPagoDTO = new MedioPagoDTO();
        medioPagoDTO.setIdMedioPago(medioPago.getMedioPagoId());
        return medioPagoDTO;
    }

    private DocumentoComercialDTO getTestDocumentoComercial() {
        Pais pais = new Pais("Argentina");
        Moneda moneda = new Moneda("Australes");
        paisService.save(pais);
        monedaService.save(moneda);

        PaisDTO paisDTO = new PaisDTO();
        paisDTO.setPaisId(pais.getPaisId());

        MonedaDTO monedaDTO = new MonedaDTO();
        monedaDTO.setMonedaId(moneda.getMonedaId());

        DocumentoComercialDTO documentoComercialDTO = new DocumentoComercialDTO();
        documentoComercialDTO.setTipoDocumento(1);
        documentoComercialDTO.setNumero(123);
        documentoComercialDTO.setPais(paisDTO);
        documentoComercialDTO.setMoneda(monedaDTO);
        return documentoComercialDTO;
    }

    private ProveedorDTO getTestProveedor() {
        Proveedor proveedor = new ProveedorPersona(null, "Juan", "132224243");
        proveedorService.save(proveedor);
        ProveedorDTO proveedorDTO = new ProveedorDTO();
        proveedorDTO.setIdProveedor(proveedor.getProveedorId());
        return proveedorDTO;
    }

    private Entidad getTestEntidad(String name){
        EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder().withNombre(name).build();
        entidadService.save(entidad);
        return entidad;
    }

    private DetalleOperacionDTO getTestDetalle(){
        Item item = new Item("Heladera");
        DetalleOperacionDTO detalleOperacion = new DetalleOperacionDTO();
        detalleOperacion.setCantidad(5);
        detalleOperacion.setPrecio(10F);
        itemService.save(item);
        detalleOperacion.setItem(new ItemDTO(item.getItemId(),  null , null));
        return detalleOperacion;
    }

    private Usuario getTestUsuario(Entidad entidad){
        Usuario usuario = new Usuario("pepe", "pepe", "pepa");
        TipoUsuario tipoUsuario = new Admin();
        usuario.setUsuariosEntidad(Arrays.asList(new UsuarioEntidad(entidad, tipoUsuario, usuario)));
        usuarioService.save(usuario);
        return usuario;
    }


}
