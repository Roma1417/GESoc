package utn.dds.tpAnual.validador;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utn.dds.tpAnual.builders.UsuarioBuilder;
import utn.dds.tpAnual.criterioCompra.ValidadorContrasenia;
import utn.dds.tpAnual.usuario.Usuario;

public class ValidadorContraseniaTest {

	private ValidadorContrasenia validadorTest = ValidadorContrasenia.getInstance();
	
    @Test
    public void contraseniaEnTop() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario")
    			.withContrasenia("q1w2e3r4t5")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaEnSuperDiccionario() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario")
    			.withContrasenia("vjht008")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaValida() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario valido")
    			.withContrasenia("superContraseniaSegura123")
    			.build();
    	assertTrue(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaProhibida() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("Usuario")
    			.withContrasenia("SuperContraseniaSegura123DeUsuario")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaProhibida2() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("usuario")
    			.withContrasenia("informacion")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaEnDiccionario() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("usuario")
    			.withContrasenia("michelle")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaReptitiva() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario valido")
    			.withContrasenia("contraseniaNoTanSegura1234")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void otraContraseniaRepetitiva() {
    	Usuario usuario = new UsuarioBuilder()
    			.withNombre("un usuario valido")
    			.withContrasenia("1contraseniaNoTaaaanSegura")
    			.build();
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
}
