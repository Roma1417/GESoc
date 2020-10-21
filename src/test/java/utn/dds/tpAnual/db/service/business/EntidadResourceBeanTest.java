package utn.dds.tpAnual.db.service.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.EntidadJuridicaEmpresaBuilder;
import utn.dds.tpAnual.db.dto.categoria.CategoriaDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.transaccion.ItemDTO;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.CategoriaNombreCorto;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.entidad.EntidadJuridica;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.entity.usuario.Admin;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.entity.usuario.UsuarioEntidad;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.CategoriaService;
import utn.dds.tpAnual.db.service.jpaService.EntidadService;
import utn.dds.tpAnual.db.service.jpaService.ItemService;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@MockBean(ProgramadorDeTareas.class)
@DataJpaTest(showSql=false)
@DirtiesContext
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class EntidadResourceBeanTest {

    @Autowired
    private EntidadService entidadService;

    @Test
    public void getEntidadesControllerConUnaEntidad(){
        Entidad entidad = new EntidadJuridicaEmpresaBuilder().buildEntidadCompletaSinRequisitos();
        this.entidadService.save(entidad);
        String username = entidad.getUsuariosEntidad().get(0).getUsuario().getUsuario();
        PageableRequest pageableRequest = new PageableRequest(username, 1L, 20L);
        Page<Entidad> entidades = this.entidadService.getEntidadesRelated(username, entidad.getNombre(),pageableRequest);
        assertTrue(entidades.toList().get(0).getNombre().equals(entidad.getNombre()));
    }

    @Test
    public void getEntidadesControllerTodasEntidadesDelUsuario(){
        EntidadJuridica entidad = new EntidadJuridicaEmpresaBuilder().buildEntidadCompletaSinRequisitos();
        Usuario usuario = entidad.getUsuariosEntidad().get(0).getUsuario();
        entidad.addUsuarioEntidad(new UsuarioEntidad(entidad.getEntidadesBase().get(0), new Admin(), usuario));
        entidad.addUsuarioEntidad(new UsuarioEntidad(entidad.getEntidadesBase().get(1), new Admin(), usuario));
        this.entidadService.save(entidad);

        PageableRequest pageableRequest = new PageableRequest(usuario.getUsuario(), 1L, 20L);
        Page<Entidad> entidades = this.entidadService.getEntidadesRelated(usuario.getUsuario(), null , pageableRequest);
        assertTrue(entidades.toList().size() == 3);
    }

}
