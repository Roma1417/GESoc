package utn.dds.tpAnual.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.entity.transaccion.MedioPago;

import java.util.List;
import java.util.Set;

@Repository
public interface DetalleOperacionRepository extends JpaRepository<DetalleOperacion, Long> {

    @Query("SELECT det FROM DetalleOperacion det " +
            " WHERE det.detalleOperacionId IN (:ids)")
    List<DetalleOperacion> findAllByIds(@Param("ids")Set<Long> keySet);

}
