package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;

import java.util.List;

@Repository
public interface EntidadRepository extends JpaRepository<Entidad, Long> {

    @Query("SELECT e FROM Entidad e WHERE e.nombre = :nombre")
    List<Entidad> getEntidadesByNombre(@Param("nombre") String nombre);

}
