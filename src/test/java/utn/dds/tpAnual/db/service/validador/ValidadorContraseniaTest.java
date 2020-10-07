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
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario")
    			.withContrasenia("q1w2e3r4t5")
				.withUsuario("unusuario")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaEnSuperDiccionario() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario")
    			.withContrasenia("vjht008")
				.withUsuario("unusuario2")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaValida() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario valido")
    			.withContrasenia("superContraseniaSegura123")
				.withUsuario("unusuariovalido")
    			.build();
    	assertTrue(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaProhibida() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("Usuario")
    			.withContrasenia("SuperContraseniaSegura123DeUsuario")
				.withUsuario("usuarioprohibido")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaProhibida2() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("usuario")
    			.withContrasenia("informacion")
				.withUsuario("usuarioinfo")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaEnDiccionario() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("usuario")
    			.withContrasenia("michelle")
				.withUsuario("michelleusuario")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaReptitiva() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario valido")
    			.withContrasenia("contraseniaNoTanSegura1234")
				.withUsuario("unusuariovalido2")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void otraContraseniaRepetitiva() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario valido")
    			.withContrasenia("1contraseniaNoTaaaanSegura")
				.withUsuario("unusuariovalido3")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
}
