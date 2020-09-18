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

    @Query("SELECT e FROM Egreso e " +
            " WHERE e.entidadRealizadora = :entidad " +
            " AND NOT EXISTS (SELECT i FROM Ingreso i " +
            "   INNER JOIN i.EgresosAsociados eg " +
            "   WHERE eg = e)")
    List<Egreso> getEgresosByEntidadRealizadora(@Param("entidad") Entidad entidad);

    @Query("SELECT e FROM Egreso e " +
            " LEFT JOIN FETCH e.presupuestos p " +
            " LEFT JOIN FETCH e.revisores us " +
            " LEFT JOIN FETCH us.bandejaMensajes m " +
            " WHERE e.operacionId = :operacionIdEgreso")
    List<Egreso> getEgresoById(@Param("operacionIdEgreso")Long operacionId);

    @Query("SELECT e FROM Egreso e " +
            " LEFT JOIN FETCH e.presupuestos p " +
            " LEFT JOIN FETCH e.revisores u " +
            " LEFT JOIN FETCH u.bandejaMensajes m " +
            " WHERE e.resultadoValidacion is null")
    List<Egreso> getEgresosSinValidar();
}
