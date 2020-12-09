package utn.dds.tpAnual.db.service.business;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.configuracion.ConfiguracionEnum;
import utn.dds.tpAnual.db.dto.categoria.CategoriaDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.usuario.UserDTO;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.service.jpaService.ConfiguracionService;
import utn.dds.tpAnual.db.service.jpaService.UsuarioService;
import utn.dds.tpAnual.db.service.security.SecurityData;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioResourceBean {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ConfiguracionService configuracionService;

    public UserDTO login(String username, String contrasenia){
        Usuario user = usuarioService.getUsuarioByUsername(username);
        if (user == null) {
            throw new SecurityException("Usuario invalido");
        }
        if (user.getCantidadIntentos() >= configuracionService.getIntValue(ConfiguracionEnum.CANTIDAD_INTENTOS_LOGIN)) {
            throw new SecurityException("Usuario bloqueado");
        }
        if (!user.matchContrasenia(contrasenia)) {
            user.addCantidadIntentos();
            usuarioService.save(user);
            throw new SecurityException("Credenciales inválidas");
        }
        return new UserDTO().from(user);
    }

    public String getJWTToken(String username) {
        String secretKey = SecurityData.getInstance().getKey();
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("utn")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return SecurityData.getInstance().getPREFIX() + token;
    }

    public boolean administraEntidadesDe(Usuario usuarioAdm, Usuario usuario) {
        List<Entidad> entidadesUsuario = usuario.getUsuariosEntidad().stream()
                .map(usuarioEntidad -> usuarioEntidad.getEntidad()).collect(Collectors.toList());
        return usuarioAdm.getUsuariosEntidad().stream()
                .anyMatch(usuarioEntidad -> usuarioEntidad.puedeVerMensajesDeOtros() && entidadesUsuario
                        .contains(usuarioEntidad.getEntidad()));
    }

    public UserDTO getUser(String username) {
        Usuario user = usuarioService.getUsuarioByUsername(username);
        return new UserDTO().from(user);
    }

    public PageableResponse<UserDTO, Usuario> getUsuarios(PageableRequest pageableRequest, String nombreUsuario) {
        Page<Usuario> usuarios = usuarioService.getUsuarios(pageableRequest, nombreUsuario);
        return new PageableResponse().fromPage(usuarios, new UserDTO());
    }
}
