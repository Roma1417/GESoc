package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.repository.ActividadRepository;
import utn.dds.tpAnual.db.repository.EntitdadRepository;
import utn.dds.tpAnual.entidad.Entidad;

import java.util.List;

@Service
    public class EntidadService extends CustomJPAService<Entidad> {

    @Autowired
    private EntitdadRepository entitdadRepository;

    @Override
    public JpaRepository<Entidad, Long> getRepository() {
        return entitdadRepository;
    }

}
