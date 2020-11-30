package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.configuracion.Configuracion;

import java.util.List;

@Repository
public interface ConfiguracionRepository extends JpaRepository<Configuracion, Long> {

    @Query("SELECT c FROM Configuracion c WHERE c.key LIKE :key")
    Configuracion getConfiguracionByKey(@Param("key") String key);
}
