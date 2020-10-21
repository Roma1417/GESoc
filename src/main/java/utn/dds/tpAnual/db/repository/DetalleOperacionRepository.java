package utn.dds.tpAnual.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.MedioPago;

@Repository
public interface DetalleOperacionRepository extends JpaRepository<DetalleOperacion, Long> {


}
