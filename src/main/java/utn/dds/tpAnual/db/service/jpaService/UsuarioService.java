package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.MensajeDTO;
import utn.dds.tpAnual.db.dto.UserDTO;
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

}
