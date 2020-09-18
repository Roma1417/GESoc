package utn.dds.tpAnual.db.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import utn.dds.tpAnual.db.entity.ubicacion.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

	@Query("SELECT c FROM Ciudad c WHERE c.idAPI IN (:listadoIdsAPI)")
	List<Ciudad> getAllByIdAPI(@Param("listadoIdsAPI") Collection<String> IDsAPI);

}
