package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.ubicacion.MonedaDTO;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.service.business.MonedaResourceBean;

@RestController
@CrossOrigin(origins="https://gesoc-app.herokuapp.com", allowCredentials = "true")
@RequestMapping("/api/moneda")
public class MonedaController {

    @Autowired
    private MonedaResourceBean monedaResourceBean;

    @RequestMapping("")
    public PageableResponse<MonedaDTO, Moneda> getItems(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                        @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage,
                                                        @RequestParam(name ="name", required = false) String monedaName){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<MonedaDTO, Moneda> monedas = monedaResourceBean.getMonedas(pageableRequest, monedaName);
        return monedas;
    }
}
