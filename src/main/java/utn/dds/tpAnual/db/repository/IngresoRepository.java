package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

import java.util.List;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {

    @Query("SELECT i FROM Ingreso i WHERE i.entidadRealizadora = :entidad AND i.EgresosAsociados IS EMPTY")
    List<Ingreso> getIngresosByEntidadRealizadora(@Param("entidad") Entidad entidad);

    @Query("SELECT i FROM Ingreso i WHERE i.codigoOperacion = :codigo")
    List<Ingreso> getIngresoByCodigoOperacion(@Param("codigo") int codigo);
}
