package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.CategoriaNombreCorto;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriaServiceTest {

    @Autowired
    private CategoriaService categoriaService;

    @Test
    public void persistenceTest() {
        Categoria unaCategoria = CategoriaNombreCorto.getInstance();
        categoriaService.save(unaCategoria);
        Categoria mismaCategoria = categoriaService.getCategoriaByDescripcion(unaCategoria.getDescripcion());
        assertTrue(mismaCategoria.getDescripcion().equals(unaCategoria.getDescripcion()));
    }

}