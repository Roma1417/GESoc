package utn.dds.tpAnual.db.service.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.EntidadJuridicaEmpresaBuilder;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.entidad.EntidadJuridica;
import utn.dds.tpAnual.db.entity.usuario.Admin;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.entity.usuario.UsuarioEntidad;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.CategoriaService;
import utn.dds.tpAnual.db.service.jpaService.EntidadService;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@MockBean(ProgramadorDeTareas.class)
@DataJpaTest(showSql=false)
@DirtiesContext
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaResourceBeanTest {

    @Autowired
    private CategoriaResourceBean categoriaResourceBean;

    @Autowired
    private CategoriaService categoriaService;

    @Test
    public void getCategoriasByNombreExactoSuccess(){
        Categoria categoria = new Categoria("Una categoria");
        categoriaService.save(categoria);
        PageableRequest pageableRequest = new PageableRequest(null, 1l, 20l);
        assertTrue(categoriaResourceBean.getCategorias(pageableRequest, "Una categoria")
            .getData().get(0).getId().equals(categoria.getIdCategoria()));
    }

    @Test
    public void getCategoriasByNombreParcialSuccess(){
        Categoria categoria = new Categoria("Una categoria");
        categoriaService.save(categoria);
        PageableRequest pageableRequest = new PageableRequest(null, 1l, 20l);
        assertTrue(categoriaResourceBean.getCategorias(pageableRequest, "catego")
                .getData().get(0).getId().equals(categoria.getIdCategoria()));
    }

    @Test
    public void getCategoriasByNombreNoExistenteEmpty(){
        Categoria categoria = new Categoria("Una categoria");
        categoriaService.save(categoria);
        PageableRequest pageableRequest = new PageableRequest(null, 1l, 20l);
        assertTrue(categoriaResourceBean.getCategorias(pageableRequest, "nombre no existente")
                .getData().isEmpty());
    }

}
