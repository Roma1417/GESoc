package utn.dds.tpAnual.db.service.business;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.UserDTO;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.service.jpaService.UsuarioService;
import utn.dds.tpAnual.db.service.security.SecurityData;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioResourceBean {
    @Autowired
    private UsuarioService usuarioService;

    public UserDTO login(String username, String contrasenia){
        Usuario user = usuarioService.getUsuarioByUsername(username);
        if (!user.matchContrasenia(contrasenia)) {
            throw new SecurityException("Usuario invalido");
        }
        String token = getJWTToken(username);
        return new UserDTO(username, token);
    }

    private String getJWTToken(String username) {
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
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    public UserDTO getMensajes(String username) {
        return null;
    }
}
