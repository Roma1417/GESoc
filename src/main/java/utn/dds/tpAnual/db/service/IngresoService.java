package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.repository.IngresoRepository;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

import java.util.List;

@Service
    public class IngresoService extends CustomJPAService<Ingreso> {

    @Autowired
    private IngresoRepository ingresoRepository;

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

}
