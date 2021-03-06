package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;
import utn.dds.tpAnual.db.repository.DireccionPostalRepository;

import java.util.List;

@Service
public class DireccionPostalService extends CustomJPAService<DireccionPostal> {

    @Autowired
    private DireccionPostalRepository direccionPostalRepository;

    @Override
    public JpaRepository<DireccionPostal, Long> getRepository() {
        return direccionPostalRepository;
    }

    public DireccionPostal getFirstDireccionByCalle(String calle){
        List<DireccionPostal> direcciones = direccionPostalRepository.getDireccionPostalByCalle(calle);
        return direcciones.isEmpty()? null : direcciones.get(0);
    }

}
