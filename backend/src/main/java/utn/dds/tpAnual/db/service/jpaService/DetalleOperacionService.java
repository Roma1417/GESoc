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
import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.repository.DetalleOperacionRepository;
import utn.dds.tpAnual.db.repository.EgresoRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
    public class DetalleOperacionService extends CustomJPAService<DetalleOperacion> {

    @Autowired
    private DetalleOperacionRepository detalleOperacionRepository;

    @Override
    public JpaRepository<DetalleOperacion, Long> getRepository() {
        return detalleOperacionRepository;
    }

    public List<DetalleOperacion> findAllByIds(Set<Long> keySet) {
        return detalleOperacionRepository.findAllByIds(keySet);
    }
}
