package utn.dds.tpAnual.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    @Query("SELECT m FROM Mensaje m WHERE m.asunto = :asunto")
    List<Mensaje> getMensajeByAsunto(@Param("asunto") String asunto);

    @Query(value = "SELECT m FROM Mensaje m " +
            " JOIN FETCH m.usuario u " +
            " WHERE u.usuarioId = :userID",
            countQuery = "SELECT COUNT(m) FROM Mensaje m " +
                    " WHERE m.usuario.usuarioId = :userID")
    Page<Mensaje> getMensajesByUsername(@Param("userID") Long userID, Pageable pageable);

}
