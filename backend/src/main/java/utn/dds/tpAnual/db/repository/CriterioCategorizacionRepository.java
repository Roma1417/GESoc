package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion.CriterioCategorizacion;

@Repository
public interface CriterioCategorizacionRepository extends JpaRepository<CriterioCategorizacion, Long> {

    @Query("SELECT c FROM CriterioCategorizacion c WHERE c.descripcion LIKE :descripcion")
    CriterioCategorizacion getCriterioCategorizacionByDescripcion(@Param("descripcion") String descripcion);
}
