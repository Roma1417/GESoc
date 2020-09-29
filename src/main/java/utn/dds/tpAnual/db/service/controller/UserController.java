package utn.dds.tpAnual.db.service.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utn.dds.tpAnual.db.dto.UserDTO;
import utn.dds.tpAnual.db.service.security.SecurityData;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping("hi")
    public String helloMessage(){
        return "Hi!";
    }

    @RequestMapping("test")
    public String testPermission(){
        return "Allowed!";
    }

    @PostMapping("auth")
    public UserDTO login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
        //Acá se validaría el usuario

        //Si da ok
        String token = getJWTToken(username);
        UserDTO user = new UserDTO(username, token);
        return user;
    }

//    //TODO: httponly
//    @PostMapping("login")
//    public String loginHttpOnly(@RequestParam("user") String username, @RequestParam("password") String pwd, HttpServletResponse response) {
//        //valido
//
//        // create a cookie
//        String token = getJWTToken(username);
//        Cookie cookie = new Cookie("Authorization", token);
//        //add cookie to response
//        response.addCookie(cookie);
//        return "Hecho";
//    }

    private String getJWTToken(String username) {
        String secretKey = SecurityData.getInstance().getKey();
        secretKey = "pepe";
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
}
