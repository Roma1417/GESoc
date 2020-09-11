package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.repository.EntitdadRepository;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

@Service
    public class EntidadService extends CustomJPAService<Entidad> {

    @Autowired
    private EntitdadRepository entitdadRepository;

    @Override
    public JpaRepository<Entidad, Long> getRepository() {
        return entitdadRepository;
    }

}
