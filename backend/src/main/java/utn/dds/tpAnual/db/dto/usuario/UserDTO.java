package utn.dds.tpAnual.db.dto.usuario;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.usuario.Usuario;

public class UserDTO extends StandardDTO<Usuario> {
    private String username;
    private String nombre;

    public UserDTO(){

    }

    public UserDTO(String username, String nombre) {
        this.username = username;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public UserDTO from(Usuario object) {
        return new UserDTO(object.getUsuario(), object.getNombre());
    }

    @Override
    public Usuario toEntity() {
        return null;
    }
}
