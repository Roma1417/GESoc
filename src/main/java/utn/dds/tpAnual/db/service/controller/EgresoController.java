package utn.dds.tpAnual.db.service.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utn.dds.tpAnual.db.dto.EgresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;

@RestController
@RequestMapping("/api/transaccion")
public class EgresoController {

    @RequestMapping("egreso")
    public PageableResponse<EgresoDTO> getEgresos(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                  @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage){
        SecurityContextHolder.getContext().getAuthentication();
        PageableRequest pageableRequest = new PageableRequest(page, itemsPerPage);
        return null;
    }
}
