package utn.dds.tpAnual.builders;

import utn.dds.tpAnual.db.entity.usuario.Usuario;

public class UsuarioBuilder {
	private String nombre;
	private String contrasenia;
    private String usuario;
	
	public UsuarioBuilder withNombre(String nombre){
        this.nombre = nombre;
        return this;
    }
	public UsuarioBuilder withContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
        return this;
    }
    public UsuarioBuilder withUsuario(String usuario){
        this.usuario = usuario;
        return this;
    }
    public Usuario build(){
        return new Usuario(nombre, usuario, contrasenia);
    }
	
}
