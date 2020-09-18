package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.afip.Actividad;

import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    @Query("SELECT a FROM Actividad a WHERE a.nombreActividad LIKE :nombre")
    List<Actividad> getActividadByNombreActividad(@Param("nombre") String nombre);

}
