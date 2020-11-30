package utn.dds.tpAnual.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c FROM Categoria c WHERE c.descripcion LIKE :descripcion")
    Categoria getCategoriaByDescripcion(@Param("descripcion") String descripcion);


    @Query("SELECT c FROM Categoria c " +
            " WHERE (:descripcion IS NULL OR upper(c.descripcion) LIKE concat('%', upper(:descripcion), '%'))")
    Page<Categoria> getCategoriaByDescripcionLike(@Param("descripcion") String descripcion, Pageable pageable);
}
