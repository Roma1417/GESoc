package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.MensajeDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.repository.MensajeRepository;

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

    public Page<Mensaje> getMensajesByUsername(Long userID, PageableRequest pageableRequest) {
        Pageable pageable = pageableRequest.toPageable();
        return mensajeRepository.getMensajesByUsername(userID, pageable);
    }

}
