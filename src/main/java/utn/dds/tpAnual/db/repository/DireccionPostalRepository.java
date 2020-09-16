package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;

import java.util.List;

@Repository
public interface DireccionPostalRepository extends JpaRepository<DireccionPostal, Long> {

    @Query("SELECT d FROM DireccionPostal d WHERE d.calle = :calle")
    List<DireccionPostal> getDireccionPostalByCalle(@Param("calle") String Calle);

}
