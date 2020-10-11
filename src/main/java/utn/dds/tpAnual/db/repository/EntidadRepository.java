package utn.dds.tpAnual.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntidadRepository extends JpaRepository<Entidad, Long> {

    @Query("SELECT e FROM Entidad e WHERE e.nombre = :nombre")
    List<Entidad> getEntidadesByNombre(@Param("nombre") String nombre);

    @Query("SELECT e FROM Entidad e " +
            " WHERE e.entidadId = :idEntidad" +
            " AND e IN (SELECT en FROM Usuario u " +
            " INNER JOIN u.usuariosEntidad ue " +
            " INNER JOIN ue.entidad en " +
            "WHERE u.usuarioId = :usuarioId)")
    Optional<Entidad> findAllRelated(@Param("usuarioId")Long usuarioId,@Param("idEntidad") Long idEntidad);


    @Query(value = "SELECT e FROM Entidad e " +
            " JOIN e.usuariosEntidad ue " +
            " JOIN ue.usuario u " +
            " WHERE u.usuario = :username AND (:entidadName IS NULL OR upper(e.nombre) " +
            " LIKE CONCAT('%', upper(:entidadName), '%'))",
            countQuery = "SELECT COUNT(e) FROM Entidad e " +
                    " JOIN e.usuariosEntidad ue " +
                    " JOIN ue.usuario u " +
                    " WHERE u.usuario = :username AND (:entidadName IS NULL OR upper(e.nombre) " +
                    " LIKE CONCAT('%', upper(:entidadName), '%'))")
    Page<Entidad> getEntidadesRelated(@Param("username") String usuario,
                                   @Param("entidadName") String entidadName,
                                   Pageable pageable);
}
