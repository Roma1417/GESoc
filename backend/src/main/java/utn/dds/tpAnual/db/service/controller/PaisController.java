package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.transaccion.ItemDTO;
import utn.dds.tpAnual.db.dto.ubicacion.PaisDTO;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.service.business.ItemResourceBean;
import utn.dds.tpAnual.db.service.business.PaisResourceBean;

@RestController
@CrossOrigin(origins="https://gesoc-app.herokuapp.com", allowCredentials = "true")
@RequestMapping("/api/pais")
public class PaisController {

    @Autowired
    private PaisResourceBean paisResourceBean;

    @RequestMapping("")
    public PageableResponse<PaisDTO, Pais> getItems(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                    @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage,
                                                    @RequestParam(name ="name", required = false) String paisName){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<PaisDTO, Pais> paises = paisResourceBean.getPaises(pageableRequest, paisName);
        return paises;
    }
}
