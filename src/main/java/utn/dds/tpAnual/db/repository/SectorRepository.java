package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.afip.Sector;

import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

    @Query("SELECT s FROM Sector s WHERE s.nombreSector LIKE :nombre")
    List<Sector> getSectorByNombre(@Param("nombre") String nombre);

}
