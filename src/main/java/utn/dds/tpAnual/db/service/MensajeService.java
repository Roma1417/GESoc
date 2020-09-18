package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.repository.MensajeRepository;
import utn.dds.tpAnual.db.repository.UsuarioRepository;

import java.util.List;

@Service
public class MensajeService extends CustomJPAService<Mensaje> {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Override
    public JpaRepository<Mensaje, Long> getRepository() {
        return mensajeRepository;
    }

    public Mensaje getFirstMensajeByAsunto(String asunto){

        List<Mensaje> mensajes = mensajeRepository.getMensajeByAsunto(asunto);
        return mensajes.isEmpty() ? null : mensajes.get(0);

    }
}
