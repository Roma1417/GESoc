package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import utn.dds.tpAnual.db.dto.transaccion.EgresoDTO;
import utn.dds.tpAnual.db.dto.transaccion.IngresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.service.business.IngresoResourceBean;

@RestController
@CrossOrigin(origins="https://gesoc-app.herokuapp.com", allowCredentials = "true")
@RequestMapping("/api/transaccion")
public class IngresoController {

    @Autowired
    private IngresoResourceBean ingresoResourceBean;

    @RequestMapping("ingreso")
    public PageableResponse<IngresoDTO, Ingreso> getIngresos(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                             @RequestParam(name ="itemsPerPage", defaultValue = "20")
                                                                     Long itemsPerPage){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<IngresoDTO, Ingreso> ingresos = ingresoResourceBean.getIngresos(pageableRequest, username);
        return ingresos;
    }

    @RequestMapping("ingreso/{ingresoId}")
    public PageableResponse<IngresoDTO, Ingreso> getIngresosById(@RequestParam(name ="page", defaultValue = "1") Long page,
                                    @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage,
                                    @PathVariable("ingresoId") Long ingresoId){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<IngresoDTO, Ingreso> ingresos = ingresoResourceBean.getIngresosById(pageableRequest, username, ingresoId);
        return ingresos;
    }

    @PostMapping("ingreso")
    public IngresoDTO crearIngreso(@RequestBody IngresoDTO ingresoDTO){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        IngresoDTO ingresoCreado = ingresoResourceBean.crearIngreso(ingresoDTO, username);
        return ingresoCreado;
    }

}
