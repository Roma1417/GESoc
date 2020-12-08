package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.complex.VinculacionEgresoIngresoDTO;
import utn.dds.tpAnual.db.dto.complex.VinculacionProyectoEgresoDTO;
import utn.dds.tpAnual.db.dto.complex.VinculacionProyectoIngresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.transaccion.IngresoDTO;
import utn.dds.tpAnual.db.dto.transaccion.ProyectoFinanciamientoDTO;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.DocumentoComercial;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.transaccion.ProyectoFinanciamiento;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.service.jpaService.*;
import utn.dds.tpAnual.db.service.rules.EgresoRules;
import utn.dds.tpAnual.db.service.rules.IngresoRules;
import utn.dds.tpAnual.db.service.rules.ProyectoFinanciamientoRules;

import java.util.Optional;

@Service
public class ProyectoFinanciamientoResourceBean {

    @Autowired
    private ProyectoFinanciamientoService proyectoFinanciamientoService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private EgresoService egresoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EntidadService entidadService;


    public PageableResponse<ProyectoFinanciamientoDTO, ProyectoFinanciamiento> getProyectos(PageableRequest pageableRequest, String username) {
        Page<ProyectoFinanciamiento> proyectos = proyectoFinanciamientoService.findAllRelated(pageableRequest, username);
        return new PageableResponse().fromPage(proyectos, new ProyectoFinanciamientoDTO());
    }

    public PageableResponse<ProyectoFinanciamientoDTO, ProyectoFinanciamiento> getProyectosById(PageableRequest pageableRequest, String username, Long proyectoId) {
        Page<ProyectoFinanciamiento> proyectos = proyectoFinanciamientoService.findAllRelatedById(pageableRequest, username, proyectoId);
        return new PageableResponse().fromPage(proyectos, new ProyectoFinanciamientoDTO());
    }

    public void vincularEgreso(VinculacionProyectoEgresoDTO vinculacion) {
        Optional<Egreso> egresoOptional = egresoService.findFullById(vinculacion.getEgresoId());
        Optional<ProyectoFinanciamiento> proyectoOptional = proyectoFinanciamientoService.findById(vinculacion.getProyectoId());
        if (!egresoOptional.isPresent() || !proyectoOptional.isPresent()){
            throw new RuntimeException("Los registros no fueron encontrados");
        }
        Egreso egreso = egresoOptional.get();
        ProyectoFinanciamiento proyecto = proyectoOptional.get();
        ProyectoFinanciamientoRules.getInstance().validarVinculacion(proyecto, egreso);
        proyecto.vincularEgreso(egreso);
        proyectoFinanciamientoService.save(proyecto);
    }

    public void vincularIngreso(VinculacionProyectoIngresoDTO vinculacion) {
        Optional<ProyectoFinanciamiento> proyectoOptional = proyectoFinanciamientoService
                .findById(vinculacion.getProyectoId());
        Optional<Ingreso> ingresoOptional = ingresoService.findById(vinculacion.getIngresoId());
        if (!proyectoOptional.isPresent() || !ingresoOptional.isPresent()){
            throw new RuntimeException("Los registros no fueron encontrados");
        }
        ProyectoFinanciamiento proyecto = proyectoOptional.get();
        Ingreso ingreso = ingresoOptional.get();
        ProyectoFinanciamientoRules.getInstance().validarVinculacion(proyecto, ingreso);
        proyecto.vincularIngreso(ingreso);
        proyectoFinanciamientoService.save(proyecto);
        ingresoService.save(ingreso);
    }

    public ProyectoFinanciamientoDTO crearProyecto(ProyectoFinanciamientoDTO proyectoDTO, String username){
        ProyectoFinanciamiento proyecto = new ProyectoFinanciamiento();
        Usuario usuario = usuarioService.getUsuarioByUsername(username);
        Usuario director = usuarioService.getUsuarioByUsername(proyectoDTO.getDirector().getUsername());
        Optional<Entidad> entidadRealizadora = entidadService
                .findAllRelated(usuario, proyectoDTO.getEntidadRealizadora().getIdEntidad());

        ProyectoFinanciamientoRules.getInstance()
                .validarCrearProyectoFinanciamiento(proyectoDTO, entidadRealizadora, director);

        proyecto.setEntidadRealizadora(entidadRealizadora.get());
        proyecto.setDirector(director);
        proyecto.setMontoSinPresupuesto(proyectoDTO.getMontoMaximoSinPresupuestos());
        proyecto.setPresupuestosMinimos(proyectoDTO.getPresupuestosMinimos());

        proyectoFinanciamientoService.save(proyecto);
        proyectoDTO.setId(proyecto.getProyectoId());
        return proyectoDTO;
    }

}
