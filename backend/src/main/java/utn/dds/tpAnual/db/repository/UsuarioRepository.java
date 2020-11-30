package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;
import utn.dds.tpAnual.db.entity.usuario.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    List<Usuario> getUsuarioByNombre(@Param("nombre") String name);

    @Query("SELECT u FROM Usuario u WHERE u.usuario = :usuario")
    Usuario getUsuarioByUsuario(@Param("usuario") String usuario);

    @Query("SELECT u FROM Usuario u " +
            " JOIN FETCH u.usuariosEntidad ue " +
            " JOIN FETCH ue.entidad " +
            " WHERE u.usuarioId = :userID OR u.usuario = :username")
    Usuario getUsuarioConEntidadesByIdOrUser(@Param("userID") Long userID, @Param("username") String username);

}
