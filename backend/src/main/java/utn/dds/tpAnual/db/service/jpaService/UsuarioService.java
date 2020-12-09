package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.usuario.UserDTO;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService extends CustomJPAService<Usuario> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public JpaRepository<Usuario, Long> getRepository() {
        return usuarioRepository;
    }

    public Usuario getFirstUsuarioByNombre(String nombreUsuario){
        List<Usuario> usuarios = usuarioRepository.getUsuarioByNombre(nombreUsuario);
        return usuarios.isEmpty() ? null : usuarios.get(0);

    }

    public Usuario getUsuarioByUsername(String usuario) {
        return usuarioRepository.getUsuarioByUsuario(usuario);
    }

    public Usuario getUsuarioConEntidadesByIdOrUser(Long userID, String user) {
        return usuarioRepository.getUsuarioConEntidadesByIdOrUser(userID, user);
    }

    public void saveIfNotExists(Usuario usuario){
        if (usuario != null) {
            if (usuario.getUsuarioId() == null ||
                    !existsById(usuario.getUsuarioId())){
                save(usuario);
            }
        }
    }

    public Page<Usuario> getUsuarios(PageableRequest pageableRequest, String nombreUsuario) {
        return usuarioRepository.getUsuarioByNameLike(nombreUsuario, pageableRequest.toPageable());
    }

    @Override
    public Object getEntity(Object entity) {
        return new UserDTO().from((Usuario) entity);
    }
}
