package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.repository.EntidadRepository;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
    public class EntidadService extends CustomJPAService<Entidad> {

    @Autowired
    private EntidadRepository entidadRepository;

    @Autowired
    private CriterioVinculacionService criterioVinculacionService;

    @Override
    public JpaRepository<Entidad, Long> getRepository() {
        return entidadRepository;
    }

    public Entidad getFirstEntidadByNombre(String nombre){
        List<Entidad> entidades = entidadRepository.getEntidadesByNombre(nombre);
        return entidades.isEmpty()? null : entidades.get(0);
    }

    @Override
    public void save(Entidad entity) {
        if (entity.getCriterioVinculacion() != null) {
            if (entity.getCriterioVinculacion().getCriterioId() != null){
                criterioVinculacionService.existsById(entity.getCriterioVinculacion().getCriterioId());
            } else {
                criterioVinculacionService.save(entity.getCriterioVinculacion());
            }
        }
        super.save(entity);
    }

    @Override
    public void saveAll(Collection<Entidad> entities) {
        entities.forEach(this::save);
    }

    public void saveIfNotExists(Entidad entidad){
        if (entidad != null) {
            if (entidad.getEntidadId() == null ||
                    !existsById(entidad.getEntidadId())){
                save(entidad);
            }
        }
    }

    public Optional<Entidad> findAllRelated(Usuario usuario, Long idEntidad) {
        return entidadRepository.findAllRelated(usuario.getUsuarioId(), idEntidad);
    }

    public Page<Entidad> getEntidadesRelated(String usuario, String entidadName, PageableRequest pageableRequest) {
        Pageable pageable = pageableRequest.toPageable();
        return entidadRepository.getEntidadesRelated(usuario, entidadName, pageable);
    }
}
