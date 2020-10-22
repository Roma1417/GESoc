package utn.dds.tpAnual.db.service.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.dto.categoria.CategoriaDTO;
import utn.dds.tpAnual.db.dto.complex.VinculacionItemCategoriaDTO;
import utn.dds.tpAnual.db.dto.transaccion.ItemDTO;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion.CriterioCategorizacion;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.CategoriaService;
import utn.dds.tpAnual.db.service.jpaService.ItemService;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@MockBean(ProgramadorDeTareas.class)
@DataJpaTest(showSql=false)
@DirtiesContext
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ItemResourceBeanTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ItemResourceBean itemResourceBean;

    @Test
    public void vincularItemCategoriaValidosSuccess(){
        Item item = new Item("Un item");
        CriterioCategorizacion criterioCategorizacion = new CriterioCategorizacion("Criterio ciudades");
        Categoria categoria = new Categoria("Ciudad 1");

        criterioCategorizacion.setCategorias(Arrays.asList(categoria));
        itemService.save(item);
        categoriaService.save(categoria);

        itemResourceBean.vincularCategoria(new VinculacionItemCategoriaDTO(item.getItemId(), categoria.getIdCategoria()));
        assertTrue(item.getCategorias().get(0).getDescripcion().equals(categoria.getDescripcion()));
    }

    @Test
    public void vincularCategoriaNoExistenteError(){
        Item item = new Item("Un item");

        itemService.save(item);

        ItemDTO itemDTO = new ItemDTO().from(item);
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setDescripcion("No existe");

        assertThrows(RuntimeException.class,() -> {
            itemResourceBean.vincularCategoria(new VinculacionItemCategoriaDTO(itemDTO.getId(), 2l));
        });
    }

    @Test
    public void vincularItemNoExisteError(){
        Categoria categoria = new Categoria("Una categoria");

        categoriaService.save(categoria);

        CategoriaDTO categoriaDTO = new CategoriaDTO().from(categoria);
        categoriaDTO.setDescripcion("No existe");

        assertThrows(RuntimeException.class,() -> {
            itemResourceBean.vincularCategoria(new VinculacionItemCategoriaDTO(2l, categoriaDTO.getId()));
        });
    }
}
