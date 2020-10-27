package utn.dds.tpAnual.db.service.rules;

import org.springframework.beans.factory.annotation.Autowired;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.entity.usuario.UsuarioEntidad;
import utn.dds.tpAnual.db.service.jpaService.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioRules {

    @Autowired
    UsuarioService usuarioService;

    private static final UsuarioRules instance = new UsuarioRules();

    private UsuarioRules(){

    }

    public static UsuarioRules getInstance(){
        return instance;
    }

    public void validarGetMensajes(String usernameRequest, Long usuarioIdMensajes){
        Usuario usuarioRequest = usuarioService.getUsuarioConEntidadesByIdOrUser(null, usernameRequest);
        Usuario usuarioMensajes = usuarioService.getUsuarioConEntidadesByIdOrUser(usuarioIdMensajes, null);
        if(usuarioMensajes == null) {
            throw new RuntimeException("El usuario de quien desea consultar los mensajes " +
                    "no existe o no posee entidades asociadas.");
        }
        if(usuarioRequest == null) {
            throw new RuntimeException("Usted no posee entidades asociadas.");
        }
        if(usuarioRequest.getUsuarioId() != usuarioMensajes.getUsuarioId() &&
                !usuarioAdministraEntidadesDe(usuarioRequest, usuarioMensajes)) {
            throw new RuntimeException("Usted no es administrador de alguna " +
                    "de las entidades asociadas al usuario: " + usuarioMensajes.getUsuario());
        }
    }

    private boolean usuarioAdministraEntidadesDe(Usuario usuarioRequest, Usuario usuarioMensajes) {
        List<Entidad> entidadesUsuarioMensajes = usuarioMensajes.getUsuariosEntidad()
                .stream().map(usuarioEntidad -> usuarioEntidad.getEntidad()).collect(Collectors.toList());
        return usuarioRequest.getUsuariosEntidad().stream().anyMatch(usuarioEntidad ->
            usuarioEntidad.puedeVerMensajesDeOtros() &&  entidadesUsuarioMensajes.contains(usuarioEntidad.getEntidad()));
    }
}
