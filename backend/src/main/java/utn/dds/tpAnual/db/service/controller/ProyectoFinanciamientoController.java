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
@CrossOrigin(origins="https://gesoc-app.herokuapp.com", allowCredentials = "true")
@RequestMapping("/api/transaccion")
public class ProyectoFinanciamientoController {

    @Autowired
    private ProyectoFinanciamientoResourceBean proyectoFinanciamientoResourceBean;

    @RequestMapping("proyecto")
    public PageableResponse<ProyectoFinanciamientoDTO, ProyectoFinanciamiento> getProyectos(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                                                           @RequestParam(name ="itemsPerPage", defaultValue = "20")
                                                                     Long itemsPerPage){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<ProyectoFinanciamientoDTO, ProyectoFinanciamiento> proyectos = proyectoFinanciamientoResourceBean.getProyectos(pageableRequest, username);
        return proyectos;
    }

    @RequestMapping("proyecto/{proyectoId}")
    public PageableResponse<ProyectoFinanciamientoDTO, ProyectoFinanciamiento> getProyectosById(@RequestParam(name ="page", defaultValue = "1") Long page,
                                    @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage,
                                    @PathVariable("proyectoId") Long proyectoId){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<ProyectoFinanciamientoDTO, ProyectoFinanciamiento> proyectos = proyectoFinanciamientoResourceBean.getProyectosById(pageableRequest, username, proyectoId);
        return proyectos;
    }

    @PostMapping("proyecto")
    public ProyectoFinanciamientoDTO crearProyecto(@RequestBody ProyectoFinanciamientoDTO proyectoDTO){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProyectoFinanciamientoDTO proyectoCreado = proyectoFinanciamientoResourceBean.crearProyecto(proyectoDTO, username);
        return proyectoCreado;
    }

    @PostMapping("vincularEgreso")
    public String vincularEgreso(@RequestBody VinculacionProyectoEgresoDTO vinculacion){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        proyectoFinanciamientoResourceBean.vincularEgreso(vinculacion);
        return "Ok";
    }

    @PostMapping("vincularIngreso")
    public String vincularIngreso(@RequestBody VinculacionProyectoIngresoDTO vinculacion){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        proyectoFinanciamientoResourceBean.vincularIngreso(vinculacion);
        return "Ok";
    }


}
