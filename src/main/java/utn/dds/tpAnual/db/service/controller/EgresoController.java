package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utn.dds.tpAnual.db.dto.EgresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.service.jpaService.EgresoService;

import java.util.Arrays;

@RestController
@RequestMapping("/api/transaccion")
public class EgresoController {

    @Autowired
    private EgresoService egresoService;

    @RequestMapping("egreso")
    public PageableResponse<EgresoDTO> getEgresos(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                  @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        //logica service
        PageableResponse<EgresoDTO> egresos = null;
        return egresos;
    }

    @PostMapping("egreso")
    public EgresoDTO crearEgreso(EgresoDTO egresoDTO){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //logica service
        EgresoDTO egresoCreado = null;
        return egresoCreado;
    }
}
