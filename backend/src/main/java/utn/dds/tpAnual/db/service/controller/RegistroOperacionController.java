package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utn.dds.tpAnual.db.dto.auditoria.RegistroOperacionDTO;
import utn.dds.tpAnual.db.dto.categoria.CategoriaDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.mongo.auditoria.TipoOperacion;
import utn.dds.tpAnual.db.mongo.entity.RegistroOperacion;
import utn.dds.tpAnual.db.service.business.CategoriaResourceBean;
import utn.dds.tpAnual.db.service.business.RegistroOperacionResourceBean;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/registroOperacion")
public class RegistroOperacionController {

    @Autowired
    private RegistroOperacionResourceBean registroOperacionResourceBean;

    @RequestMapping("")
    public PageableResponse<RegistroOperacionDTO, RegistroOperacion> getOperaciones(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                                                    @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage,
                                                                                    @RequestParam(name ="nombreClase", required = false) String nombreClase,
                                                                                    @RequestParam(name ="tipoOperacion", required = false) String tipoOperacion
                                                                            ){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);

        PageableResponse<RegistroOperacionDTO, RegistroOperacion>  operaciones = registroOperacionResourceBean.getRegistroOperacion(pageableRequest,
                tipoOperacion != null ? TipoOperacion.valueOf(tipoOperacion) : null, nombreClase);
        return operaciones;
    }

    @RequestMapping("tipoOperacion")
    public TipoOperacion[] getTipoOperacion(){
        return TipoOperacion.values();
    }

    @RequestMapping("nombreClases")
    public Collection<String> getNombreClases(){
        return registroOperacionResourceBean.getNombreClases();
    }
}
