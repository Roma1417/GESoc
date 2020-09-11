package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

@Repository
public interface EntitdadRepository extends JpaRepository<Entidad, Long> {
}
