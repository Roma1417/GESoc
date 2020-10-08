package utn.dds.tpAnual.db.service.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.dto.CategoriaDTO;
import utn.dds.tpAnual.db.dto.ItemDTO;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.CategoriaNombreCorto;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.CategoriaService;
import utn.dds.tpAnual.db.service.jpaService.ItemService;

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
        Categoria categoria = CategoriaNombreCorto.getInstance();

        itemService.save(item);
        categoriaService.save(categoria);

        ItemDTO itemDTO = new ItemDTO().from(item);
        CategoriaDTO categoriaDTO = new CategoriaDTO().from(categoria);
        itemDTO.setCategoria(categoriaDTO);

        itemResourceBean.vincularCategoria(itemDTO);
        assertTrue(item.getCategoria().getDescripcion().equals(categoria.getDescripcion()));
    }

    @Test
    public void vincularCategoriaNoExistenteError(){
        Item item = new Item("Un item");

        itemService.save(item);

        ItemDTO itemDTO = new ItemDTO().from(item);
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setDescripcion("No existe");
        itemDTO.setCategoria(categoriaDTO);

        assertThrows(RuntimeException.class,() -> {
            itemResourceBean.vincularCategoria(itemDTO);
        });
    }

    @Test
    public void vincularItemNoExisteError(){
        Item item = new Item("Un item");
        Categoria categoria = CategoriaNombreCorto.getInstance();

        categoriaService.save(categoria);

        ItemDTO itemDTO = new ItemDTO().from(item);
        CategoriaDTO categoriaDTO = new CategoriaDTO().from(categoria);
        categoriaDTO.setDescripcion("No existe");
        itemDTO.setCategoria(categoriaDTO);

        assertThrows(RuntimeException.class,() -> {
            itemResourceBean.vincularCategoria(itemDTO);
        });
    }
}
