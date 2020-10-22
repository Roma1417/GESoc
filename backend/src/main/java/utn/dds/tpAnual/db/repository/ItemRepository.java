package utn.dds.tpAnual.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT it FROM Item it " +
            " LEFT JOIN FETCH it.categorias " +
            " WHERE (:nombreItem IS NULL OR it.descripcion LIKE CONCAT('%', :nombreItem, '%') )",
            countQuery = "SELECT COUNT(DISTINCT it) FROM Item it " +
                    " WHERE (:nombreItem IS NULL OR it.descripcion LIKE CONCAT('%', :nombreItem, '%') )")
    Page<Item> getItemsByDescripcionLike(@Param("nombreItem") String descripcion, Pageable pageable);
}
