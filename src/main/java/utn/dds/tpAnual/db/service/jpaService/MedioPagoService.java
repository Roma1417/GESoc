package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.transaccion.MedioPago;
import utn.dds.tpAnual.db.repository.MedioPagoRepository;


@Service
public class MedioPagoService extends CustomJPAService<MedioPago> {

    @Autowired
    private MedioPagoRepository medioPagoRepository;

    @Override
    public JpaRepository<MedioPago, Long> getRepository() {
        return medioPagoRepository;
    }
}
