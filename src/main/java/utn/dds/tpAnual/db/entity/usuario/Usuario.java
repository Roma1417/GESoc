package utn.dds.tpAnual.db.entity.usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import utn.dds.tpAnual.db.entity.afip.RequisitoSectorEmpresa;

import javax.persistence.*;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long usuarioId;

	@Column(name = "CANTIDAD_INTENTOS")
	private int cantidadIntentos;

	@Column(name = "USUARIO", unique = true, nullable = false)
	private String usuario;

	@Column(name = "CONTRASENIA", nullable = false)
	private String contrasenia;

	@Column(name = "FECHA_ESPERA")
	private LocalDateTime fechaEspera;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UsuarioEntidad> usuariosEntidad;

	@OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Mensaje> bandejaMensajes;
	
	public Usuario(String nombre, String usuario, String contrasenia) {
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.usuario = usuario;
	}

	public Usuario() {
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}

	public boolean matchContrasenia(String contraseniaAComparar) {
		//TODO hash
		return contraseniaAComparar.equals(contrasenia);
	}

	public Set<Mensaje> getBandejaMensajes() {
		return bandejaMensajes;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getCantidadIntentos() {
		return cantidadIntentos;
	}

	public void setCantidadIntentos(int cantidadIntentos) {
		this.cantidadIntentos = cantidadIntentos;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public LocalDateTime getFechaEspera() {
		return fechaEspera;
	}

	public void setFechaEspera(LocalDateTime fechaEspera) {
		this.fechaEspera = fechaEspera;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<UsuarioEntidad> getUsuariosEntidad() {
		return usuariosEntidad;
	}

	public void setUsuariosEntidad(List<UsuarioEntidad> usuariosEntidad) {
		this.usuariosEntidad = usuariosEntidad;
	}

	public void setBandejaMensajes(Set<Mensaje> bandejaMensajes) {
		this.bandejaMensajes = bandejaMensajes;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
		if (bandejaMensajes == null){
			bandejaMensajes = new HashSet<>();
		}
		bandejaMensajes.add(mensaje);
	}
		
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("nombre", nombre)
				.append("usuario", usuario)
			    .append("contraseña", contrasenia)
			    .append("\nmensajes", bandejaMensajes)
			    .toString();
	}
}