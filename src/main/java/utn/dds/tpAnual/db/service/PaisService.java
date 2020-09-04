package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.repository.ActividadRepository;
import utn.dds.tpAnual.db.repository.PaisRepository;
import utn.dds.tpAnual.ubicacion.Pais;

import java.util.List;

@Service
public class PaisService extends CustomJPAService<Pais> {

    @Autowired
    private PaisRepository paisRepository;

    @Override
    public JpaRepository<Pais, Long> getRepository() {
        return paisRepository;
    }

}
