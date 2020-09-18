package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.afip.Sector;
import utn.dds.tpAnual.db.repository.SectorRepository;

import java.util.List;

@Service
public class SectorService extends CustomJPAService<Sector> {

    @Autowired
    private SectorRepository sectorRepository;

    @Override
    public JpaRepository<Sector, Long> getRepository() {
        return sectorRepository;
    }

    public Sector getPrimerSectorByNombre(String nombre){

        List<Sector> sectores = sectorRepository.getSectorByNombre(nombre);
        return sectores.isEmpty() ? null : sectores.get(0);

    }
}
