package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.usuario.MensajeDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.service.jpaService.MensajeService;
import utn.dds.tpAnual.db.service.rules.UsuarioRules;

@Service
public class MensajeResourceBean {
    @Autowired
    private MensajeService mensajeService;

    public PageableResponse<MensajeDTO, Mensaje> getMensajesFrom(PageableRequest pageableRequest) {
        Page<Mensaje> mensajes = mensajeService.getMensajesByUsername(pageableRequest.getUser(), pageableRequest);
        return new PageableResponse().fromPage(mensajes, new MensajeDTO());
    }
}
