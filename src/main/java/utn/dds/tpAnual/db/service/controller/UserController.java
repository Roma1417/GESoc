package utn.dds.tpAnual.db.service.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utn.dds.tpAnual.db.dto.ItemDTO;
import utn.dds.tpAnual.db.dto.MensajeDTO;
import utn.dds.tpAnual.db.dto.UserDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.repository.UsuarioRepository;
import utn.dds.tpAnual.db.service.business.MensajeResourceBean;
import utn.dds.tpAnual.db.service.business.UsuarioResourceBean;
import utn.dds.tpAnual.db.service.jpaService.UsuarioService;
import utn.dds.tpAnual.db.service.security.SecurityData;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UsuarioResourceBean usuarioResourceBean;

    @Autowired
    private MensajeResourceBean mensajeResourceBean;

    @RequestMapping("hi")
    public String helloMessage(){
        return "Hi!";
    }

    @RequestMapping("test")
    public String testPermission(){
        return "Allowed!";
    }

    @PostMapping("auth")
    public String login(@RequestParam("user") String username, @RequestParam("password") String pwd,
                         HttpServletResponse response) {
        UserDTO userDTO = usuarioResourceBean.login(username, pwd);
        Cookie cookie = new Cookie("Authorization", userDTO.getToken());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(600000);
        cookie.setPath(null);
        response.addCookie(cookie);
        return "Ok";
    }

    @RequestMapping("mensajes")
    public PageableResponse<MensajeDTO, Mensaje> getMensajes(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                             @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<MensajeDTO, Mensaje> mensajes = mensajeResourceBean.getMensajesFrom(pageableRequest, username);
        return mensajes;
    }
}
