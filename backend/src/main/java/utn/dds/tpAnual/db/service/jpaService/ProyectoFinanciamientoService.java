package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.transaccion.PresupuestoDTO;
import utn.dds.tpAnual.db.dto.transaccion.ProyectoFinanciamientoDTO;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.transaccion.Presupuesto;
import utn.dds.tpAnual.db.entity.transaccion.ProyectoFinanciamiento;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.repository.ProyectoFinanciamientoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
    public class ProyectoFinanciamientoService extends CustomJPAService<ProyectoFinanciamiento> {

    @Autowired
    private ProyectoFinanciamientoRepository proyectoFinanciamientoRepository;

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EgresoService egresoService;

    @Autowired
    private IngresoService ingresoService;

    @Override
    public JpaRepository<ProyectoFinanciamiento, Long> getRepository() {
        return proyectoFinanciamientoRepository;
    }

    public ProyectoFinanciamiento getProyectoById(Long operacionId) {
        List<ProyectoFinanciamiento> proyectos = proyectoFinanciamientoRepository.getProyectoById(operacionId);
        return proyectos.isEmpty() ? null : proyectos.get(0);
    }

    public Optional<ProyectoFinanciamiento> findById(Long proyectoId) {
        return proyectoFinanciamientoRepository.findById(proyectoId);
    }

    public Page<ProyectoFinanciamiento>findAllRelated(PageableRequest pageableRequest, String username) {
        Pageable pageable = pageableRequest.toPageable();
        Usuario usuario = usuarioService.getUsuarioByUsername(username);
        return proyectoFinanciamientoRepository.getAllRelated(usuario.getUsuarioId(), pageable);
    }

    public Page<ProyectoFinanciamiento>findAllRelatedById(PageableRequest pageableRequest, String username, Long proyectoId) {
        Pageable pageable = pageableRequest.toPageable();
        Usuario usuario = usuarioService.getUsuarioByUsername(username);
        return proyectoFinanciamientoRepository.getAllRelatedById(usuario.getUsuarioId(), pageable, proyectoId);
    }

    public void saveIfNotExists(ProyectoFinanciamiento proyectoFinanciamiento){
        if (proyectoFinanciamiento != null) {
            usuarioService.saveIfNotExists(proyectoFinanciamiento.getDirector());
            if (proyectoFinanciamiento.getProyectoId() == null ||
                    !existsById(proyectoFinanciamiento.getProyectoId())){
                save(proyectoFinanciamiento);
            }
        }
    }

    @Override
    public void saveAll(Collection<ProyectoFinanciamiento> entities) {
        entities.forEach(this::save);
    }

    @Override
    public Object getEntity(Object entity) {
        return new ProyectoFinanciamientoDTO().from((ProyectoFinanciamiento) entity);
    }
}
