package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.categoria.CategoriaDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.ubicacion.PaisDTO;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.service.jpaService.CategoriaService;
import utn.dds.tpAnual.db.service.jpaService.PaisService;

@Service
public class CategoriaResourceBean {

    @Autowired
    private CategoriaService categoriaService;

    public PageableResponse<CategoriaDTO, Categoria> getCategorias(PageableRequest pageableRequest, String nombreCategoria) {
        Page<Categoria> categorias = categoriaService.getCategorias(pageableRequest, nombreCategoria);
        return new PageableResponse().fromPage(categorias, new CategoriaDTO());
    }
}
