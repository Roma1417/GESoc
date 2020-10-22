package utn.dds.tpAnual.db.service.security;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if (existeJWTToken(request, response) && esPathSeguro(request)) {
                Claims claims = validateToken(request);
                if (claims.get("authorities") != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        } catch (SignatureException | ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }
    }

    private boolean esPathSeguro(HttpServletRequest request) {
        return !"/api/auth".equals(request.getRequestURI()) && !"/api/hi".equals(request.getRequestURI());
    }

    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = getTokenCookieValue(request).replace(SecurityData.getInstance().getPREFIX(), "");
        return Jwts.parser().setSigningKey(SecurityData.getInstance().getKey().getBytes())
                .parseClaimsJws(jwtToken).getBody();
    }

    /**
     * Metodo para autenticarnos dentro del flujo de Spring
     *
     * @param claims
     */
    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List) claims.get("authorities");

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
        Optional<Cookie> authCookie = getTokenCookie(request);
        return authCookie.isPresent();
    }

    private Optional<Cookie> getTokenCookie(HttpServletRequest request) {
        return Arrays.stream(request.getCookies()).filter(cookie -> SecurityData.getInstance().getAUTH_COOKIE_NAME()
                .equals(cookie.getName())).findFirst();
    }

    private String getTokenCookieValue(HttpServletRequest request){
        return getTokenCookie(request).get().getValue();
    }

}
