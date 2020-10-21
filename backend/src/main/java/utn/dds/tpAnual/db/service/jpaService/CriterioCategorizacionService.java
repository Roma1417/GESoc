package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion.CriterioCategorizacion;
import utn.dds.tpAnual.db.repository.CriterioCategorizacionRepository;

@Service
public class CriterioCategorizacionService extends CustomJPAService<CriterioCategorizacion> {

    @Autowired
    private CriterioCategorizacionRepository criterioCategorizacionRepository;

    @Override
    public JpaRepository<CriterioCategorizacion, Long> getRepository() {
        return criterioCategorizacionRepository;
    }

    public CriterioCategorizacion getCriterioCategorizacionByDescripcion(String descripcion){
        CriterioCategorizacion criterioCategorizacion = criterioCategorizacionRepository.getCriterioCategorizacionByDescripcion(descripcion);
        return criterioCategorizacion;
    }
}
