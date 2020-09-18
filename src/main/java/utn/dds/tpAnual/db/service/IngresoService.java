package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.repository.IngresoRepository;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.List;

@Service
    public class IngresoService extends CustomJPAService<Ingreso> {

    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private EntidadService entidadService;

    @Override
    public JpaRepository<Ingreso, Long> getRepository() {
        return ingresoRepository;
    }

    public List<Ingreso> getIngresoVinculableByEntidadRealidadora(Entidad entidad){
        return ingresoRepository.getIngresosByEntidadRealizadora(entidad);
    }

    public Ingreso getPrimerIngresoByCodigoOperacion(int codigo){

        List<Ingreso> ingresos = ingresoRepository.getIngresoByCodigoOperacion(codigo);
        return ingresos.isEmpty() ? null : ingresos.get(0);

    }

    @Override
    public void save(Ingreso entity) {
        entidadService.saveIfNotExists(entity.getEntidadRealizadora());
        super.save(entity);
    }

    @Override
    public void saveAll(Collection<Ingreso> entities) {
        entities.forEach(this::save);
    }

}
