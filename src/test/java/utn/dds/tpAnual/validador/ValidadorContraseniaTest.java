package utn.dds.tpAnual.validador;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utn.dds.tpAnual.usuario.Usuario;
import utn.dds.tpAnual.validador.ValidadorContrasenia;

public class ValidadorContraseniaTest {

	private ValidadorContrasenia validadorTest = ValidadorContrasenia.getInstance();
	
    @Test
    public void contraseniaEnTop() {
    	Usuario usuario = new Usuario("un usuario", "q1w2e3r4t5");
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaEnSuperDiccionario() {
    	Usuario usuario = new Usuario("un usuario", "vjht008");
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaValida() {
    	Usuario usuario = new Usuario("un usuario valido", "superContraseniaSegura123");
    	assertTrue(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaProhibida() {
    	Usuario usuario = new Usuario("Usuario", "SuperContraseniaSegura123DeUsuario");
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaProhibida2() {
    	Usuario usuario = new Usuario("usuario", "informacion");
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaEnDiccionario() {
    	Usuario usuario = new Usuario("usuario", "michelle");
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void contraseniaReptitiva() {
    	Usuario usuario = new Usuario("un usuario valido", "contraseniaNoTanSegura1234");
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
    
    @Test
    public void otraContraseniaRepetitiva() {
    	Usuario usuario = new Usuario("un usuario valido", "1contraseniaNoTaaaanSegura");
    	assertFalse(validadorTest.esContraseniaValida(usuario));
    }
}
