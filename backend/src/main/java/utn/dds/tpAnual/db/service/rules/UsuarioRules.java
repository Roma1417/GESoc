package utn.dds.tpAnual.db.service.rules;

import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;

public class UsuarioRules {
    private static final UsuarioRules instance = new UsuarioRules();

    private UsuarioRules(){

    }

    public static UsuarioRules getInstance(){
        return instance;
    }

    public void validarGetMensajes(String usernameRequest, Long usuarioIdMensajes){

    }
}
