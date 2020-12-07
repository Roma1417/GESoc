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
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.transaccion.ProyectoFinanciamiento;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProyectoFinanciamientoRepository extends JpaRepository<ProyectoFinanciamiento, Long> {

//    @Query("SELECT DISTINCT p FROM ProyectoFinanciamiento p " +
//            " WHERE p.entidadRealizadora = :entidad ")
//    List<ProyectoFinanciamiento> getProyectosByEntidadRealizadora(@Param("entidad") Entidad entidad);
//
    @Query("SELECT p FROM ProyectoFinanciamiento p " +
            " WHERE p.proyectoId = :operacionIdProyecto")
    List<ProyectoFinanciamiento> getProyectoById(@Param("operacionIdProyecto") Long operacionId);

    @Query("SELECT DISTINCT p FROM ProyectoFinanciamiento p" +
            " WHERE p.proyectoId = :proyectoId")
    Optional<ProyectoFinanciamiento> findById(@Param("proyectoId") Long proyectoId);

    @Query(value = "SELECT p FROM ProyectoFinanciamiento p " +
            " JOIN FETCH p.entidadRealizadora entidad " +
            " JOIN FETCH p.director director " +
            " WHERE entidad IN (SELECT entidadDeUsuario FROM Usuario u " +
            " JOIN u.usuariosEntidad ue " +
            " JOIN ue.entidad entidadDeUsuario " +
            " WHERE u.usuarioId = :usuarioId) ",
            countQuery = "SELECT COUNT(p) FROM ProyectoFinanciamiento p " +
                    " JOIN p.entidadRealizadora entidad " +
                    " JOIN p.director director " +
                    " WHERE entidad IN (SELECT entidadDeUsuario FROM Usuario u " +
                    " JOIN u.usuariosEntidad ue " +
                    " JOIN ue.entidad entidadDeUsuario " +
                    " WHERE u.usuarioId = :usuarioId) ")
    Page<ProyectoFinanciamiento> getAllRelated(@Param("usuarioId") Long usuarioId, Pageable pageable);
}


