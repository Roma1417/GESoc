package utn.dds.tpAnual.db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Presupuesto;

import java.util.List;

@Repository
public interface PresupuestoRepository extends JpaRepository<Presupuesto, Long> {

    @Query("SELECT p FROM Presupuesto p WHERE p.operacionId = :operacionId")
    Presupuesto getPresupuestoById(Long operacionId);
    
    @Query(value = "SELECT p FROM Presupuesto p " +
            " JOIN FETCH p.egreso " +
            " JOIN FETCH p.documentoComercial d " +
            " JOIN FETCH p.entidadRealizadora entidad " +
            " JOIN FETCH p.detallesPrecio det " +
            " JOIN FETCH det.detalleOperacion do " +
            " JOIN FETCH do.item items " +
            " WHERE (:categoria IS NULL OR " +
            "   items IN ( SELECT i FROM Item i " +
            "   INNER JOIN i.categorias c " +
            "   WHERE c.descripcion = :categoria))  " +
            "   AND entidad IN ( SELECT entidadesUsuario from Entidad entidadesUsuario " +
            "   JOIN entidadesUsuario.usuariosEntidad ue " +
            "   JOIN ue.usuario usuario " +
            "   WHERE usuario.usuario = :username ) ",
            countQuery  = "SELECT COUNT(p) FROM Presupuesto p " +
            " JOIN p.egreso " +
            " JOIN p.documentoComercial d " +
            " JOIN p.entidadRealizadora entidad " +
            " JOIN p.detallesPrecio det " +
            " JOIN det.detalleOperacion do " +
            " JOIN do.item items " +
                    " WHERE (:categoria IS NULL OR " +
                    "   items IN ( SELECT i FROM Item i " +
                    "   INNER JOIN i.categorias c " +
                    "   WHERE c.descripcion = :categoria))  " +
            "   AND entidad IN ( SELECT entidadesUsuario from Entidad entidadesUsuario " +
            "   JOIN entidadesUsuario.usuariosEntidad ue " +
            "   JOIN ue.usuario usuario " +
            "   WHERE usuario.usuario = :username )")
    Page<Presupuesto> getPresupuestoRelatedByCategoria(Pageable pageable, @Param("categoria")String categoria,
                                                       @Param("username")String username);



}
