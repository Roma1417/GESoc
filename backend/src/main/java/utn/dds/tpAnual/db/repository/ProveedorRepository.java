package utn.dds.tpAnual.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    @Query("SELECT p FROM Proveedor p WHERE p.direccionPostal = :dp")
    List<Proveedor> getProveedorByDireccionPostal(@Param("dp") DireccionPostal direccionPostal);

    @Query(value = " SELECT p FROM Proveedor p " +
            " WHERE (:proveedorName IS NULL OR upper(p.nombreRazonSocial) " +
            " LIKE CONCAT('%', upper(:proveedorName), '%'))"
            , countQuery = "SELECT COUNT(p) FROM Proveedor p " +
            " WHERE (:proveedorName IS NULL OR upper(p.nombreRazonSocial) " +
            " LIKE CONCAT('%', upper(:proveedorName), '%'))")
    Page<Proveedor> getProveedoresByNameLike(Pageable pageable, @Param("proveedorName") String proveedorName);

}
