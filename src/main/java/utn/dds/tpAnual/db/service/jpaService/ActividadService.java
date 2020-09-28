package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.repository.ActividadRepository;

import java.util.List;

@Service
public class ActividadService extends CustomJPAService<Actividad> {

    @Autowired
    private ActividadRepository actividadRepository;

    @Override
    public JpaRepository<Actividad, Long> getRepository() {
        return actividadRepository;
    }

    public Actividad getPrimeraActividadByNombre(String nombre){

        List<Actividad> actividades = actividadRepository.getActividadByNombreActividad(nombre);
        return actividades.isEmpty() ? null : actividades.get(0);

    }
}
