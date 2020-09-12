package utn.dds.tpAnual.db.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import utn.dds.tpAnual.db.entity.ubicacion.Moneda;

@Repository
public interface MonedaRepository extends JpaRepository<Moneda, Long> {

    @Query("SELECT m FROM Moneda m WHERE m.idAPI IN (:listadoIdsAPI)")
    List<Moneda> getAllByIdAPI(@Param("listadoIdsAPI") Collection<String> IDsAPI);

}
