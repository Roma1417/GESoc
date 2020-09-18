package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.categorizacion.criterioVinculacion.*;
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

    public void save(CriterioVinculacionMix criterioVinculacionMix){
        if (criterioVinculacionMix.getCriteriosVinculacion() != null){
            criterioVinculacionMix.getCriteriosVinculacion().forEach(criterioVinculacion -> {
                if (criterioVinculacion.getCriterioId() == null || !existsById(criterioVinculacion.getCriterioId())){
                    save(criterioVinculacion);
                }
            });
        }
        super.save(criterioVinculacionMix);
    }

    public CriterioVinculacion getValorPrimerEgreso(){
        CriterioVinculacion criterio = CriterioVinculacionOrdenValorPrimerEgreso.getInstance();
        createIfNotExists(criterio);
        return criterio;
    }

    public CriterioVinculacion getValorPrimerIngreso(){
        CriterioVinculacion criterio = CriterioVinculacionOrdenValorPrimerIngreso.getInstance();
        createIfNotExists(criterio);
        return criterio;
    }

    public CriterioVinculacion getFecha(){
        CriterioVinculacion criterio = CriterioVinculacionFecha.getInstance();
        createIfNotExists(criterio);
        return criterio;
    }

    private void createIfNotExists(CriterioVinculacion criterio) {
        if (criterio.getCriterioId() != null && !existsById(criterio.getCriterioId())) {
            criterio.setCriterioId(null);
        }
        if (criterio.getCriterioId() == null){
            save(criterio);
        }
    }

}
