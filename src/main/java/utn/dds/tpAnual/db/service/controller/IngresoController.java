package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utn.dds.tpAnual.db.dto.IngresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.service.business.IngresoResourceBean;

@RestController
@RequestMapping("/api/transaccion")
public class IngresoController {

    @Autowired
    private IngresoResourceBean ingresoResourceBean;

    @RequestMapping("ingreso")
    public PageableResponse<IngresoDTO, Ingreso> getIngresos(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                             @RequestParam(name ="itemsPerPage", defaultValue = "20")
                                                                     Long itemsPerPage,
                                                             @RequestParam(name ="categoria")
                                                                         String categoria){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<IngresoDTO, Ingreso> ingresos = ingresoResourceBean.getIngresos(pageableRequest, categoria);
        return ingresos;
    }
}
