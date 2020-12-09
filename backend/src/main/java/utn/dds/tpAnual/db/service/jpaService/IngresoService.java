package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.repository.IngresoRepository;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
    public class IngresoService extends CustomJPAService<Ingreso> {

    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public JpaRepository<Ingreso, Long> getRepository() {
        return ingresoRepository;
    }

    public List<Ingreso> getIngresoVinculableByEntidadRealidadora(Entidad entidad){
        return ingresoRepository.getIngresosByEntidadRealizadora(entidad);
    }

    public Ingreso getPrimerIngresoByCodigoOperacion(int codigo){

        List<Ingreso> ingresos = ingresoRepository.getIngresoByCodigoOperacion(codigo);
        return ingresos.isEmpty() ? null : ingresos.get(0);

    }

    @Override
    public void save(Ingreso entity) {
        entidadService.saveIfNotExists(entity.getEntidadRealizadora());
        super.save(entity);
    }

    @Override
    public void saveAll(Collection<Ingreso> entities) {
        entities.forEach(this::save);
    }

    public Optional<Ingreso> findFullById(Long ingresoId) {
        return ingresoRepository.findFullById(ingresoId);
    }

    public Page<Ingreso>findAllRelated(PageableRequest pageableRequest, String username) {
        Pageable pageable = pageableRequest.toPageable();
        Usuario usuario = usuarioService.getUsuarioByUsername(username);
        return ingresoRepository.getAllRelated(usuario.getUsuarioId(), pageable);
    }

    public Page<Ingreso> findAllRelatedById(PageableRequest pageableRequest, String username, Long ingresoId) {
        Pageable pageable = pageableRequest.toPageable();
        Usuario usuario = usuarioService.getUsuarioByUsername(username);
        return ingresoRepository.getAllRelatedById(usuario.getUsuarioId(), pageable, ingresoId);
    }

    @Override
    public boolean puedePersistirseEnMongo() {
        return false;
    }

}
