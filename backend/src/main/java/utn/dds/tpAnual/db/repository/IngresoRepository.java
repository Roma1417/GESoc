package utn.dds.tpAnual.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {

    @Query("SELECT i FROM Ingreso i " +
            " WHERE i.entidadRealizadora = :entidad AND i.egresosAsociados IS EMPTY")
    List<Ingreso> getIngresosByEntidadRealizadora(@Param("entidad") Entidad entidad);

    @Query("SELECT i FROM Ingreso i WHERE i.codigoOperacion = :codigo")
    List<Ingreso> getIngresoByCodigoOperacion(@Param("codigo") int codigo);

    @Query("SELECT i FROM Ingreso i " +
            " JOIN FETCH i.entidadRealizadora entidad " +
            " LEFT JOIN FETCH i.egresosAsociados egresos" +
            " WHERE i.operacionId = :ingresoId")
    Optional<Ingreso> findFullById(@Param("ingresoId") Long ingresoId);

    @Query(value = "SELECT i FROM Ingreso i " +
            " JOIN FETCH i.entidadRealizadora entidad " +
            " JOIN FETCH i.documentoComercial " +
            " WHERE entidad IN (SELECT entidadDeUsuario FROM Usuario u " +
            " JOIN u.usuariosEntidad ue " +
            " JOIN ue.entidad entidadDeUsuario " +
            " WHERE u.usuarioId = :usuarioId) ",
            countQuery = "SELECT COUNT(i) FROM Ingreso i " +
                    " JOIN i.entidadRealizadora entidad " +
                    " JOIN i.documentoComercial documento" +
                    " WHERE entidad IN (SELECT entidadDeUsuario FROM Usuario u " +
                    " JOIN u.usuariosEntidad ue " +
                    " JOIN ue.entidad entidadDeUsuario " +
                    " WHERE u.usuarioId = :usuarioId) ")
    Page<Ingreso> getAllRelated(@Param("usuarioId") Long usuarioId,Pageable pageable);

    @Query(value = "SELECT i FROM Ingreso i " +
            " JOIN FETCH i.entidadRealizadora entidad " +
            " JOIN FETCH i.documentoComercial " +
            " WHERE i.operacionId = :ingresoId AND " +
            " entidad IN (SELECT entidadDeUsuario FROM Usuario u " +
            " JOIN u.usuariosEntidad ue " +
            " JOIN ue.entidad entidadDeUsuario " +
            " WHERE u.usuarioId = :usuarioId) ",
            countQuery = "SELECT COUNT(i) FROM Ingreso i " +
                    " JOIN i.entidadRealizadora entidad " +
                    " JOIN i.documentoComercial documento" +
                    " WHERE i.operacionId = :ingresoId AND " +
                    " entidad IN (SELECT entidadDeUsuario FROM Usuario u " +
                    " JOIN u.usuariosEntidad ue " +
                    " JOIN ue.entidad entidadDeUsuario " +
                    " WHERE u.usuarioId = :usuarioId) ")
    Page<Ingreso> getAllRelatedById(@Param("usuarioId") Long usuarioId, Pageable pageable,
                                    @Param("ingresoId") Long ingresoId);
}
