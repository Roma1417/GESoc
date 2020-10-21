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
import utn.dds.tpAnual.db.service.rules.IngresoRules;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@MockBean(ProgramadorDeTareas.class)
@DataJpaTest(showSql=false)
@DirtiesContext
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class IngresoResourceBeanTest {

    @Autowired
    private IngresoResourceBean ingresoResourceBean;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private MonedaService monedaService;

    @Test
    public void obtenerIngresosUsuarioRelacionadoDevuelve (){
        Entidad entidad = getTestEntidad("1");
        Usuario usuario = getTestUsuario(entidad);
        PageableRequest pageableRequest = new PageableRequest(usuario.getNombre(),1L, 5L);
        Ingreso ingreso = new Ingreso();
        ingreso.setEntidadRealizadora(entidad);
        ingresoService.save(ingreso);
        PageableResponse<IngresoDTO, Ingreso> ingresosEncontrados = ingresoResourceBean.getIngresos(pageableRequest, usuario.getNombre());
        assertTrue(!ingresosEncontrados.getData().isEmpty());
    }

    @Test
    public void obtenerIngresosUsuarioNoRelacionadoVacio (){
        Entidad entidad = getTestEntidad("1");
        Usuario usuario = getTestUsuario(entidad);
        Entidad entidad2 = getTestEntidad("2");
        PageableRequest pageableRequest = new PageableRequest(usuario.getNombre(),1L, 5L);
        Ingreso ingreso = new Ingreso();
        ingreso.setEntidadRealizadora(entidad2);
        ingresoService.save(ingreso);
        PageableResponse<IngresoDTO, Ingreso> ingresosEncontrados = ingresoResourceBean.getIngresos(pageableRequest, usuario.getNombre());
        assertTrue(ingresosEncontrados.getData().isEmpty());
    }

    @Test
    public void crearIngresoCompletoSuccess (){
        Entidad entidad = getTestEntidad("1");
        EntidadDTO entidadDTO = new EntidadDTO();
        entidadDTO.setIdEntidad(entidad.getEntidadId());
        Usuario usuario = getTestUsuario(entidad);
        IngresoDTO ingresoDTO = getTestIngresoDTO(entidadDTO);
        IngresoDTO ingresoCreado = ingresoResourceBean.crearIngreso(ingresoDTO, usuario.getUsuario());
        Optional<Ingreso> ingreso = ingresoService.findById(ingresoCreado.getIdIngreso());
        assertTrue(ingreso.get().getDescripcion().equals(ingresoDTO.getDescripcion()) &&
                ingreso.get().getTotal().equals(ingresoDTO.getTotal()) &&
                ingreso.get().getCodigoOperacion().equals(ingresoDTO.getCodigoOperacion()));
    }

    @Test
    public void crearIngresoTotalCeroError (){
        Entidad entidad = getTestEntidad("1");
        EntidadDTO entidadDTO = new EntidadDTO();
        entidadDTO.setIdEntidad(entidad.getEntidadId());
        Usuario usuario = getTestUsuario(entidad);
        IngresoDTO ingresoDTO = getTestIngresoDTO(entidadDTO);
        ingresoDTO.setTotal(0F);
        assertThrows(RuntimeException.class,() -> {
            IngresoDTO ingresoCreado = ingresoResourceBean.crearIngreso(ingresoDTO, usuario.getUsuario());
        });
    }

    private IngresoDTO getTestIngresoDTO(EntidadDTO entidadDTO) {
        IngresoDTO ingresoDTO = new IngresoDTO();
        ingresoDTO.setDocumentoComercial(getTestDocumentoComercial());
        ingresoDTO.setEntidadRealizadora(entidadDTO);
        ingresoDTO.setDescripcion("Prueba");
        ingresoDTO.setCodigoOperacion(1);
        ingresoDTO.setTotal(1F);
        return ingresoDTO;
    }

    private DocumentoComercialDTO getTestDocumentoComercial() {
        Pais pais = new Pais("Argentina");
        Moneda moneda = new Moneda("Australes");
        paisService.save(pais);
        monedaService.save(moneda);

        PaisDTO paisDTO = new PaisDTO();
        paisDTO.setIdPais(pais.getPaisId());

        MonedaDTO monedaDTO = new MonedaDTO();
        monedaDTO.setIdMoneda(moneda.getMonedaId());

        DocumentoComercialDTO documentoComercialDTO = new DocumentoComercialDTO();
        documentoComercialDTO.setTipoDocumento(1);
        documentoComercialDTO.setNumero(123);
        documentoComercialDTO.setPais(paisDTO);
        documentoComercialDTO.setMoneda(monedaDTO);
        return documentoComercialDTO;
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
        detalleOperacion.setItem(new ItemDTO(item.getItemId(), null, null , null));
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
