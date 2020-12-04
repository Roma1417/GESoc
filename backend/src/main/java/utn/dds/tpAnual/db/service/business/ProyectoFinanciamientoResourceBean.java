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

import java.util.Optional;

@Service
public class ProyectoFinanciamientoResourceBean {

    @Autowired
    private ProyectoFinanciamientoService proyectoFinanciamientoService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private EgresoService egresoService;

    public PageableResponse<ProyectoFinanciamientoDTO, ProyectoFinanciamiento> getProyectos(PageableRequest pageableRequest, String username) {
        Page<ProyectoFinanciamiento> proyectos = proyectoFinanciamientoService.findAllRelated(pageableRequest, username);
        return new PageableResponse().fromPage(proyectos, new ProyectoFinanciamientoDTO());
    }

    public PageableResponse<IngresoDTO, Ingreso> getIngresosById(PageableRequest pageableRequest, String username, Long ingresoId) {
        Page<Ingreso> ingresos = ingresoService.findAllRelatedById(pageableRequest, username, ingresoId);
        return new PageableResponse().fromPage(ingresos, new IngresoDTO());
    }
    public void vincularEgreso(VinculacionProyectoEgresoDTO vinculacion) {
        Optional<Egreso> egresoOptional = egresoService.findFullById(vinculacion.getEgresoId());
        Optional<ProyectoFinanciamiento> proyectoOptional = proyectoFinanciamientoService.findFullById(vinculacion.getProyectoId());
        if (!egresoOptional.isPresent() || !proyectoOptional.isPresent()){
            throw new RuntimeException("Los registros no fueron encontrados");
        }
        Egreso egreso = egresoOptional.get();
        ProyectoFinanciamiento proyecto = proyectoOptional.get();
        //EgresoRules.getInstance().validarVinculacion(egreso, ingreso);
        proyecto.vincularEgreso(egreso);
        egresoService.save(egreso);
    }
    public void vincularIngreso(VinculacionProyectoIngresoDTO vinculacion) {
        Optional<ProyectoFinanciamiento> proyectoOptional = proyectoFinanciamientoService.findFullById(vinculacion.getProyectoId());
        Optional<Ingreso> ingresoOptional = ingresoService.findFullById(vinculacion.getIngresoId());
        if (!proyectoOptional.isPresent() || !ingresoOptional.isPresent()){
            throw new RuntimeException("Los registros no fueron encontrados");
        }
        ProyectoFinanciamiento proyecto = proyectoOptional.get();
        Ingreso ingreso = ingresoOptional.get();
        //EgresoRules.getInstance().validarVinculacion(egreso, ingreso);
        proyecto.vincularIngreso(ingreso);
        ingresoService.save(ingreso);
    }
}
