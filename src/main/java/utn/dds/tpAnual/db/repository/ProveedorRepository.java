package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    @Query("SELECT p FROM Proveedor p WHERE p.direccionPostal = :cp")
    List<Proveedor> getProveedorByDireccionPostal(@Param("cp") int direccionPostal);

}
