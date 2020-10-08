package utn.dds.tpAnual.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.entidad.EntidadJuridica;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EgresoRepository extends JpaRepository<Egreso, Long> {

    @Query("SELECT DISTINCT e FROM Egreso e " +
            " JOIN FETCH e.detallesOperacion do " +
            " JOIN FETCH do.item i " +
            " WHERE e.entidadRealizadora = :entidad " +
            " AND NOT EXISTS (SELECT i FROM Ingreso i " +
            "   INNER JOIN i.egresosAsociados eg " +
            "   WHERE eg = e) " +
            " AND e.fecha IS NOT NULL ")
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


    @Query("SELECT e FROM Egreso e " +
            " WHERE (:dateFrom IS NULL OR e.fecha >= :dateFrom)" +
            "AND (:dateTo IS NULL OR e.fecha <= :dateTo)" +
            "AND (:dontFilterCompany = 1 OR e.entidadRealizadora IN (:companias))")
    List<Egreso> getEgresoByCompanias(@Param("companias")List<EntidadJuridica> entidadJuridicas,
                                      @Param("dontFilterCompany") boolean dontFilterByCompany,
                                      @Param("dateFrom")LocalDate from,
                                      @Param("dateTo") LocalDate to);
    @Query("SELECT e FROM Egreso e " +
            " WHERE (:cantidadPresupuestosMinimos IS NULL OR e.cantidadPresupuestosMinimos = :cantMinimos )")
    Page<Egreso> getEgresosByCantidadPresupuestosMinimos(@Param("cantMinimos")Integer cantidadPresupuestosMinimos, Pageable pageable);

    @Query("SELECT DISTINCT e FROM Egreso e " +
            " JOIN FETCH e.detallesOperacion do " +
            " JOIN FETCH do.item i " +
            " JOIN FETCH e.entidadRealizadora entidad " +
            " WHERE e.operacionId = :egresoId")
    Optional<Egreso> findFullById(@Param("egresoId") Long egresoId);
}
