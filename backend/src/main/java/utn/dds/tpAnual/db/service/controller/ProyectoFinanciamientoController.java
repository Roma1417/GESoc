package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import utn.dds.tpAnual.db.dto.complex.VinculacionEgresoIngresoDTO;
import utn.dds.tpAnual.db.dto.complex.VinculacionProyectoEgresoDTO;
import utn.dds.tpAnual.db.dto.complex.VinculacionProyectoIngresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.transaccion.IngresoDTO;
import utn.dds.tpAnual.db.dto.transaccion.ProyectoFinanciamientoDTO;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.transaccion.ProyectoFinanciamiento;
import utn.dds.tpAnual.db.service.business.IngresoResourceBean;
import utn.dds.tpAnual.db.service.business.ProyectoFinanciamientoResourceBean;

@RestController
@RequestMapping("/api/transaccion")
public class ProyectoFinanciamientoController {

    @Autowired
    private ProyectoFinanciamientoResourceBean proyectoFinanciamientoResourceBean;

    @RequestMapping("proyectoFinanciamiento")
    public PageableResponse<ProyectoFinanciamientoDTO, ProyectoFinanciamiento> getProyecto(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                                                           @RequestParam(name ="itemsPerPage", defaultValue = "20")
                                                                     Long itemsPerPage){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<ProyectoFinanciamientoDTO, ProyectoFinanciamiento> proyectos = proyectoFinanciamientoResourceBean.getProyectos(pageableRequest, username);
        return proyectos;
    }

    /*@RequestMapping("proyectoFinanciamiento/{proyectoId}")
    public PageableResponse<ProyectoFinanciamientoDTO, ProyectoFinanciamiento> getProyectosById(@RequestParam(name ="page", defaultValue = "1") Long page,
                                    @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage,
                                    @PathVariable("proyectoId") Long proyectoId){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<ProyectoFinanciamientoDTO, ProyectoFinanciamiento> proyectos = proyectoFinanciamientoResourceBean.getProyectosById(pageableRequest, username, ingresoId);
        return proyectos;
    }*/

    /*
    @PostMapping("ingreso")
    public IngresoDTO crearIngreso(@RequestBody IngresoDTO ingresoDTO){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        IngresoDTO ingresoCreado = ingresoResourceBean.crearIngreso(ingresoDTO, username);
        return ingresoCreado;
    }
    */

    @PostMapping("vincularEgreso")
    public String vincularProyectoEgreso(@RequestBody VinculacionProyectoEgresoDTO vinculacion){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        proyectoFinanciamientoResourceBean.vincularEgreso(vinculacion);
        return "Ok";
    }

    @PostMapping("vincularIngreso")
    public String vincularProyectoIngreso(@RequestBody VinculacionProyectoIngresoDTO vinculacion){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        proyectoFinanciamientoResourceBean.vincularIngreso(vinculacion);
        return "Ok";
    }


}
