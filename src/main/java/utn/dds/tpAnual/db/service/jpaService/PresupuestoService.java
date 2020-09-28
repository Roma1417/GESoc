package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.transaccion.Presupuesto;
import utn.dds.tpAnual.db.repository.PresupuestoRepository;

@Service
    public class PresupuestoService extends CustomJPAService<Presupuesto> {

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Override
    public JpaRepository<Presupuesto, Long> getRepository() {
        return presupuestoRepository;
    }

    public Presupuesto getPresupuestoById(Long operacionId) {
        return presupuestoRepository.getPresupuestoById(operacionId);
    }
}
