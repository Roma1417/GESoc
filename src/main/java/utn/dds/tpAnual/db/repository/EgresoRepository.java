package utn.dds.tpAnual.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.entidad.EntidadJuridica;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;

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


    @Query(value = "SELECT egreso FROM Egreso egreso " +
            " JOIN FETCH egreso.entidadRealizadora entidad " +
            " JOIN FETCH egreso.detallesOperacion detalles " +
            " JOIN FETCH detalles.item items " +
            " JOIN FETCH egreso.proveedor proveedor " +
            " JOIN FETCH egreso.medioPago mediPago " +
            " JOIN FETCH egreso.documentoComercial documentoComercial " +
            " WHERE (:categoria IS NULL OR items.categoria.descripcion LIKE CONCAT('%', :categoria, '%') ) " +
            " AND entidad IN ( SELECT entidadesUsuario from Entidad entidadesUsuario " +
            "   JOIN entidadesUsuario.usuariosEntidad ue " +
            "   JOIN ue.usuario usuario " +
            "   WHERE usuario.usuarioId = :userId ) ",
            countQuery  = "SELECT COUNT(DISTINCT  egreso) FROM Egreso egreso " +
            " JOIN egreso.entidadRealizadora entidad " +
            " JOIN egreso.detallesOperacion detalles " +
            " JOIN detalles.item items " +
            " JOIN egreso.proveedor proveedor " +
            " JOIN egreso.medioPago mediPago " +
            " JOIN egreso.documentoComercial documentoComercial " +
            " WHERE (:categoria IS NULL OR items.categoria.descripcion LIKE CONCAT('%', :categoria, '%') )" +
            " AND entidad IN ( SELECT entidadesUsuario from Entidad entidadesUsuario " +
                    "   JOIN entidadesUsuario.usuariosEntidad ue " +
                    "   JOIN ue.usuario usuario " +
                    "   WHERE usuario.usuarioId = :userId ) ")
    Page<Egreso> getEgresosByCategoria(Pageable pageable, @Param("categoria")String categoria, @Param("userId") long userId);
}
