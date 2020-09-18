package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.entity.usuario.UsuarioEntidad;

import java.util.List;

@Repository
public interface UsuarioEntidadRepository extends JpaRepository<UsuarioEntidad, Long> {

    @Query("SELECT u FROM UsuarioEntidad u WHERE u.usuarioEntidadId = :id")
    List<UsuarioEntidad> getUsuarioEntidadById(@Param("id") Long id);

}
