package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.usuario.UsuarioEntidad;
import utn.dds.tpAnual.db.repository.UsuarioEntidadRepository;

import java.util.List;

@Service
public class UsuarioEntidadService extends CustomJPAService<UsuarioEntidad> {

    @Autowired
    private UsuarioEntidadRepository usuarioEntidadRepository;

    @Override
    public JpaRepository<UsuarioEntidad, Long> getRepository() {
        return usuarioEntidadRepository;
    }

    public UsuarioEntidad getFirstUsuarioEntidadById(Long id){

        List<UsuarioEntidad> usuariosEntidad = usuarioEntidadRepository.getUsuarioEntidadById(id);
        return usuariosEntidad.isEmpty() ? null : usuariosEntidad.get(0);

    }
}
