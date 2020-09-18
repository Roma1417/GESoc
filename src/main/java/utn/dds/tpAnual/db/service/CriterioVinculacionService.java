package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion.CriterioVinculacion;
import utn.dds.tpAnual.db.repository.ActividadRepository;
import utn.dds.tpAnual.db.repository.CriterioVinculacionRepository;

import java.util.List;

@Service
public class CriterioVinculacionService extends CustomJPAService<CriterioVinculacion> {

    @Autowired
    private CriterioVinculacionRepository criterioVinculacionRepository;

    @Override
    public JpaRepository<CriterioVinculacion, Long> getRepository() {
        return criterioVinculacionRepository;
    }
}
