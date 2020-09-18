package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;

import java.util.Collection;
import java.util.List;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

    @Query("SELECT p FROM Pais p WHERE p.descripcion IN (:listadoNombres)")
    List<Pais> getAllByDescripciones(@Param("listadoNombres") Collection<String> descripciones);



}
