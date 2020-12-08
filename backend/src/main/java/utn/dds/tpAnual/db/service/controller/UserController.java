package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import utn.dds.tpAnual.db.dto.categoria.CategoriaDTO;
import utn.dds.tpAnual.db.dto.usuario.MensajeDTO;
import utn.dds.tpAnual.db.dto.usuario.UserDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.service.business.MensajeResourceBean;
import utn.dds.tpAnual.db.service.business.UsuarioResourceBean;
import utn.dds.tpAnual.db.service.rules.UsuarioRules;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
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
    public UserDTO login(@RequestParam("user") String username, @RequestParam("password") String pwd,
                         HttpServletResponse response) {
        UserDTO userDTO = usuarioResourceBean.login(username, pwd);
        String token = usuarioResourceBean.getJWTToken(username);
        Cookie cookie = new Cookie("Authorization", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(600000);
        cookie.setPath(null);
        response.addCookie(cookie);
        return userDTO;
    }

    @RequestMapping("user/mensajes")
    public PageableResponse<MensajeDTO, Mensaje> getMensajes(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                             @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<MensajeDTO, Mensaje> mensajes = mensajeResourceBean.getMensajesFrom(pageableRequest);
        return mensajes;
    }

    @RequestMapping("user")
    public UserDTO getUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = usuarioResourceBean.getUser(username);
        return userDTO;
    }

    @RequestMapping("users")
    public PageableResponse<UserDTO, Usuario> getUsers(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                       @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage,
                                                       @RequestParam(name ="name", required = false) String nombreUsuario){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<UserDTO, Usuario>  usuarios = usuarioResourceBean.getUsuarios(pageableRequest, nombreUsuario);
        return usuarios;
    }
}
