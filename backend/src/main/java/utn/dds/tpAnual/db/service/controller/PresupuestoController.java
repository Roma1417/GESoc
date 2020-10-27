package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import utn.dds.tpAnual.db.dto.complex.VinculacionEgresoIngresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.transaccion.EgresoDTO;
import utn.dds.tpAnual.db.dto.transaccion.PresupuestoDTO;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Presupuesto;
import utn.dds.tpAnual.db.service.business.EgresoResourceBean;
import utn.dds.tpAnual.db.service.business.PresupuestoResourceBean;

@RestController
@RequestMapping("/api/transaccion")
public class PresupuestoController {

    @Autowired
    private PresupuestoResourceBean presupuestoResourceBean;

    @RequestMapping("presupuesto")
    public PageableResponse<PresupuestoDTO, Presupuesto> getPresupuestos(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                        @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage,
                                                        @RequestParam(name ="categoria", required = false) String categoria){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<PresupuestoDTO, Presupuesto> presupuestos = presupuestoResourceBean.getPresupuestos(pageableRequest, categoria);
        return presupuestos;
    }


    @PostMapping("presupuesto")
    public PresupuestoDTO crearPresupuesto(@RequestBody PresupuestoDTO presupuestoDTO){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PresupuestoDTO presupuestoCreado = presupuestoResourceBean.crearPresupuesto(presupuestoDTO, username);
        return presupuestoCreado;
    }

}
