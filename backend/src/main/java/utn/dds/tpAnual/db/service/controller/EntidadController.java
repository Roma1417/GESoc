package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import utn.dds.tpAnual.db.dto.entidad.EntidadDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.transaccion.ItemDTO;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.service.business.EntidadResourceBean;
import utn.dds.tpAnual.db.service.business.ItemResourceBean;

@RestController
@CrossOrigin(origins="https://gesoc-app.herokuapp.com", allowCredentials = "true")
@RequestMapping("/api/entidad")
public class EntidadController {

    @Autowired
    private EntidadResourceBean entidadResourceBean;

    @RequestMapping("")
    public PageableResponse<EntidadDTO, Entidad> getEntidades(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                          @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage,
                                                          @RequestParam(name ="name", required = false) String entidadName){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<EntidadDTO, Entidad> entidades = entidadResourceBean.getEntidades(pageableRequest, username, entidadName);
        return entidades;
    }

}
