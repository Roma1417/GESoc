package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.transaccion.DetallePrecio;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;
import utn.dds.tpAnual.db.repository.DetallePrecioRepository;
import utn.dds.tpAnual.db.repository.DireccionPostalRepository;

import java.util.List;

@Service
public class DetallePrecioService extends CustomJPAService<DetallePrecio> {

    @Autowired
    private DetallePrecioRepository detallePrecioRepository;

    @Override
    public JpaRepository<DetallePrecio, Long> getRepository() {
        return detallePrecioRepository;
    }

}
