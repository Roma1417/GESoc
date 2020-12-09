package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utn.dds.tpAnual.db.dto.categoria.CategoriaDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.ubicacion.PaisDTO;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.service.business.CategoriaResourceBean;
import utn.dds.tpAnual.db.service.business.PaisResourceBean;

@RestController
@CrossOrigin(origins="https://gesoc-app.herokuapp.com", allowCredentials = "true")
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaResourceBean categoriaResourceBean;

    @RequestMapping("")
    public PageableResponse<CategoriaDTO, Categoria> getCategoria(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                                  @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage,
                                                                  @RequestParam(name ="name", required = false) String nombreCategoria){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<CategoriaDTO, Categoria>  categorias = categoriaResourceBean.getCategorias(pageableRequest, nombreCategoria);
        return categorias;
    }
}
