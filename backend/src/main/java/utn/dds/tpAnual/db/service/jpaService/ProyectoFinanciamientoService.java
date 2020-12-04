package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.entidad.EntidadJuridica;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.transaccion.ProyectoFinanciamiento;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.repository.EgresoRepository;
import utn.dds.tpAnual.db.repository.ProyectoFinanciamientoRepository;
import utn.dds.tpAnual.utils.BooleanUtils;

import java.time.LocalDate;
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

    @Override
    public JpaRepository<ProyectoFinanciamiento, Long> getRepository() {
        return proyectoFinanciamientoRepository;
    }

    public List<ProyectoFinanciamiento> getProyectosByEntidadRealizadora(Entidad entidad){
        return proyectoFinanciamientoRepository.getProyectosByEntidadRealizadora(entidad);
    }

    public ProyectoFinanciamiento getProyectoById(Long operacionId) {
        List<ProyectoFinanciamiento> proyectos = proyectoFinanciamientoRepository.getProyectoById(operacionId);
        return proyectos.isEmpty() ? null : proyectos.get(0);
    }

    public Optional<ProyectoFinanciamiento> findFullById(Long proyectoId) {
        return proyectoFinanciamientoRepository.findFullById(proyectoId);
    }

    public Page<ProyectoFinanciamiento>findAllRelated(PageableRequest pageableRequest, String username) {
        Pageable pageable = pageableRequest.toPageable();
        Usuario usuario = usuarioService.getUsuarioByUsername(username);
        return proyectoFinanciamientoRepository.getAllRelated(usuario.getUsuarioId(), pageable);
    }

    @Override
    public void save(ProyectoFinanciamiento entity) {
        entidadService.saveIfNotExists(entity.getEntidadRealizadora());
        super.save(entity);
    }

    @Override
    public void saveAll(Collection<ProyectoFinanciamiento> entities) {
        entities.forEach(this::save);
    }

}
