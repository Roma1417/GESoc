package utn.dds.tpAnual.db.service.business;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.ItemDTO;
import utn.dds.tpAnual.db.dto.MensajeDTO;
import utn.dds.tpAnual.db.dto.UserDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.service.jpaService.MensajeService;
import utn.dds.tpAnual.db.service.jpaService.UsuarioService;
import utn.dds.tpAnual.db.service.security.SecurityData;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeResourceBean {
    @Autowired
    private MensajeService mensajeService;

    public PageableResponse<MensajeDTO, Mensaje> getMensajesFrom(PageableRequest pageableRequest, String username) {
        Page<Mensaje> mensajes = mensajeService.getMensajesByUsername(username, pageableRequest);
        return new PageableResponse().fromPage(mensajes, new MensajeDTO());
    }
}
