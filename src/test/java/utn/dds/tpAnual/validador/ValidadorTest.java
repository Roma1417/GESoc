package utn.dds.tpAnual.validador;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import utn.dds.tpAnual.compra.Egreso;
import utn.dds.tpAnual.usuario.Mensaje;
import utn.dds.tpAnual.usuario.Usuario;

public class ValidadorTest {

	
    @Test
    public void notificarRevisoresEnvioOk() {
    	Validador validador = Validador.getInstance();
    	Usuario usuario = new Usuario("un usuario", "q1w2e3r4t5");
    	Usuario otroUsuario = new Usuario("un usuario", "q1w2e3r4t5");
    	List<Usuario> usuarios = Arrays.asList(usuario, otroUsuario);
    	Egreso egreso = new Egreso(10, 0, null, null, usuarios);
    	validador.notificarRevisores(egreso, true);
    	List<Mensaje> mensajeUsuario = usuario.getBandejaMensajes();
    	List<Mensaje> mensajeOtroUsuario = otroUsuario.getBandejaMensajes();
    	assertTrue("Validacion realizada con Exito".equals(mensajeUsuario.get(0).getCuerpo()) && 
    			"Validacion realizada con Exito".equals(mensajeOtroUsuario.get(0).getCuerpo()));
    }
    
    @Test
    public void notificarRevisoresEnvioError() {
    	Validador validador = Validador.getInstance();
    	Usuario usuario = new Usuario("un usuario", "q1w2e3r4t5");
    	Usuario otroUsuario = new Usuario("un usuario", "q1w2e3r4t5");
    	List<Usuario> usuarios = Arrays.asList(usuario, otroUsuario);
    	Egreso egreso = new Egreso(10, 0, null, null, usuarios);
    	validador.notificarRevisores(egreso, false);
    	List<Mensaje> mensajeUsuario = usuario.getBandejaMensajes();
    	List<Mensaje> mensajeOtroUsuario = otroUsuario.getBandejaMensajes();
    	assertTrue("Fallo de Validacion".equals(mensajeUsuario.get(0).getCuerpo()) && 
    			"Fallo de Validacion".equals(mensajeOtroUsuario.get(0).getCuerpo()));
    }
}
