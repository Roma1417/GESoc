package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.repository.EntidadRepository;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

import java.util.List;

@Service
    public class EntidadService extends CustomJPAService<Entidad> {

    @Autowired
    private EntidadRepository entidadRepository;

    @Override
    public JpaRepository<Entidad, Long> getRepository() {
        return entidadRepository;
    }

    public Entidad getFirstEntidadByNombre(String nombre){
        List<Entidad> entidades = entidadRepository.getEntidadesByNombre(nombre);
        return entidades.isEmpty()? null : entidades.get(0);
    }

}
