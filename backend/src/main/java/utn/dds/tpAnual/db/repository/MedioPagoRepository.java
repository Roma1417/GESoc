package utn.dds.tpAnual.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.entity.transaccion.MedioPago;

import java.util.List;

@Repository
public interface MedioPagoRepository extends JpaRepository<MedioPago, Long> {

    @Query(value = "SELECT m FROM MedioPago m " +
            " WHERE (:medioPagoName IS NULL OR m.instrumentoPago LIKE CONCAT('%', :medioPagoName, '%') )",
            countQuery = "SELECT COUNT(DISTINCT m) FROM MedioPago m " +
                    " WHERE (:medioPagoName IS NULL OR m.instrumentoPago LIKE CONCAT('%', :medioPagoName, '%') )")
    Page<MedioPago> getMediosDePagoByNameLike(Pageable pageable, @Param("medioPagoName") String medioPagoName);
}
