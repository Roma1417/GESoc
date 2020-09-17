package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.repository.EgresoRepository;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

import java.util.List;

@Service
    public class EgresoService extends CustomJPAService<Egreso> {

    @Autowired
    private EgresoRepository egresoRepository;

    @Override
    public JpaRepository<Egreso, Long> getRepository() {
        return egresoRepository;
    }

    public List<Egreso> getEgresosVinculablesByEntidadRealizadora(Entidad entidad){
        return egresoRepository.getEgresosByEntidadRealizadora(entidad);
    }

    public List<Egreso> getEgresosSinValidar(){
        return egresoRepository.getEgresosSinValidar();
    }

    public Egreso getEgresoById(Long operacionId) {
        List<Egreso> egresos = egresoRepository.getEgresoById(operacionId);
        return egresos.isEmpty() ? null : egresos.get(0);
    }
}
