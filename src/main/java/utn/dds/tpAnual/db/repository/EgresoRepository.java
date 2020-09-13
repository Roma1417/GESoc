package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

import java.util.List;

@Repository
public interface EgresoRepository extends JpaRepository<Egreso, Long> {

    @Query("SELECT e FROM Egreso e WHERE e.entidadRealizadora = :entidad")
    List<Egreso> getEgresosByEntidadRealizadora(@Param("entidad") Entidad entidad);

    @Query("SELECT e FROM Egreso e WHERE e.operacionId = :operacionId")
    Egreso getEgresoById(Long operacionId);
}
