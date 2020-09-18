package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.usuario.TipoUsuario;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.repository.TipoUsuarioRepository;
import utn.dds.tpAnual.db.repository.UsuarioRepository;

import java.util.List;

@Service
public class TipoUsuarioService extends CustomJPAService<TipoUsuario> {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public JpaRepository<TipoUsuario, Long> getRepository() {
        return tipoUsuarioRepository;
    }

    public TipoUsuario getFirstTipoUsuarioByDescripcion(String descripcion){

        List<TipoUsuario> tiposUsuario = tipoUsuarioRepository.getTipoUsuarioByDescripcion(descripcion);
        return tiposUsuario.isEmpty() ? null : tiposUsuario.get(0);

    }
}
