package utn.dds.tpAnual.db.service.validador;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.builders.UsuarioBuilder;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.validador.ValidadorContrasenia;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(ProgramadorDeTareas.class)
public class ValidadorContraseniaTest {

	@Autowired
	private ValidadorContrasenia validadorTest;
	
    @Test
    public void contraseniaEnTop() {
    	String contrasenia = "q1w2e3r4t5";
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario")
    			.withContrasenia(contrasenia)
				.withUsuario("unusuario")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario, contrasenia));
    }
    
    @Test
    public void contraseniaEnSuperDiccionario() {
		String contrasenia = "vjht008";
		Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario")
    			.withContrasenia(contrasenia)
				.withUsuario("unusuario2")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario, contrasenia));
    }
    
    @Test
    public void contraseniaValida() {
		String contrasenia = "superContraseniaSegura123";
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario valido")
    			.withContrasenia(contrasenia)
				.withUsuario("unusuariovalido")
    			.build();
    	assertTrue(validadorTest.esContraseniaValida(usuario, contrasenia));
    }
    
    @Test
    public void contraseniaProhibida() {
		String contrasenia = "SuperContraseniaSegura123DeUsuario";
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("Usuario")
    			.withContrasenia(contrasenia)
				.withUsuario("usuarioprohibido")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario, contrasenia));
    }
    
    @Test
    public void contraseniaProhibida2() {
		String contrasenia = "informacion";
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("usuario")
    			.withContrasenia(contrasenia)
				.withUsuario("usuarioinfo")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario, contrasenia));
    }
    
    @Test
    public void contraseniaEnDiccionario() {
		String contrasenia = "michelle";
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("usuario")
    			.withContrasenia(contrasenia)
				.withUsuario("michelleusuario")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario, contrasenia));
    }
    
    @Test
    public void contraseniaReptitiva() {
		String contrasenia = "contraseniaNoTanSegura1234";
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario valido")
    			.withContrasenia(contrasenia)
				.withUsuario("unusuariovalido2")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario, contrasenia));
    }
    
    @Test
    public void otraContraseniaRepetitiva() {
		String contrasenia = "1contraseniaNoTaaaanSegura";
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario valido")
    			.withContrasenia(contrasenia)
				.withUsuario("unusuariovalido3")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario, contrasenia));
    }
}
