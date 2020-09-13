package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;
import utn.dds.tpAnual.db.entity.ubicacion.Estado;
import utn.dds.tpAnual.db.repository.DireccionPostalRepository;
import utn.dds.tpAnual.db.repository.EstadoRepository;

@Service
public class DireccionPostalService extends CustomJPAService<DireccionPostal> {

    @Autowired
    private DireccionPostalRepository direccionPostalRepository;

    @Override
    public JpaRepository<DireccionPostal, Long> getRepository() {
        return direccionPostalRepository;
    }

}
