package utn.dds.tpAnual.usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public class Usuario {

	private List<Mensaje> bandejaMensajes;
	private int cantidadIntentos;
	private String contrasenia;
	private LocalDateTime fechaEspera;
	private String nombre;
	private List<UsuarioEntidad> usuariosEntidad;
	
	public Usuario(String nombre, String contrasenia) {
		this.nombre = nombre;
		this.contrasenia = contrasenia;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}

	public List<Mensaje> getBandejaMensajes() {
		return bandejaMensajes;
	}
	
	public boolean puedeLoguearse() {
		return fechaEspera == null 
				|| LocalDateTime.now().isAfter(fechaEspera);
	}
	
	public void loginOk() {
		cantidadIntentos = 0;
		fechaEspera = null;
	}
	
	public void intentoFallido() {
		cantidadIntentos++;
		LocalDateTime nuevaFechaEspera = LocalDateTime.now();
		nuevaFechaEspera.plusHours(cantidadIntentos * 2);
		fechaEspera = nuevaFechaEspera;
	}
	
	public void recibirMensaje(Mensaje mensaje) {
		if(bandejaMensajes == null) {
			bandejaMensajes = new ArrayList<Mensaje>();
		}
		bandejaMensajes.add(mensaje);
	}
		
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("nombre", nombre)
			    .append("contraseña", contrasenia)
			    .append("\nmensajes", bandejaMensajes)
			    .toString();
	}
}