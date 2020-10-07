package utn.dds.tpAnual.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.dto.MensajeDTO;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.entity.usuario.Usuario;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    @Query("SELECT m FROM Mensaje m WHERE m.asunto = :asunto")
    List<Mensaje> getMensajeByAsunto(@Param("asunto") String asunto);

    @Query(value = "SELECT m FROM Mensaje m " +
            " JOIN FETCH m.usuario u " +
            " WHERE (:username IS NULL OR u.usuario LIKE CONCAT('%', :username, '%') )",
            countQuery = "SELECT COUNT(DISTINCT m) FROM Mensaje m " +
                    " JOIN m.usuario u " +
                    " WHERE (:username IS NULL OR u.usuario LIKE CONCAT('%', :username, '%') )")
    Page<Mensaje> getMensajesByUsername(@Param("username") String username, Pageable pageable);

}
