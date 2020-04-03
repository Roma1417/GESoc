package utn.dds.tpAnual.seguridad;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UsuarioTest {

   
    @Test
    public void contraseniaEnTop() {
    	Usuario usuario = new Usuario("un usuario", "q1w2e3r4t5");
    	assertFalse(usuario.validarContrasenia());
    }
    
    @Test
    public void contraseniaEnSuperDiccionario() {
    	Usuario usuario = new Usuario("un usuario", "vjht008");
    	assertFalse(usuario.validarContrasenia());
    }
    
    @Test
    public void contraseniaValida() {
    	Usuario usuario = new Usuario("un usuario valido", "superContraseniaSegura123");
    	assertTrue(usuario.validarContrasenia());
    }
    
    @Test
    public void contraseniaProhibida() {
    	Usuario usuario = new Usuario("Usuario", "SuperContraseniaSegura123DeUsuario");
    	assertFalse(usuario.validarContrasenia());
    }
    
    @Test
    public void contraseniaProhibida2() {
    	Usuario usuario = new Usuario("usuario", "informacion");
    	assertFalse(usuario.validarContrasenia());
    }
    
    @Test
    public void contraseniaEnDiccionario() {
    	Usuario usuario = new Usuario("usuario", "michelle");
    	assertFalse(usuario.validarContrasenia());
    }
    
    @Test
    public void contraseniaReptitiva() {
    	Usuario usuario = new Usuario("un usuario valido", "contraseniaNoTanSegura1234");
    	assertFalse(usuario.validarContrasenia());
    }
    
    @Test
    public void otraContraseniaReptitiva() {
    	Usuario usuario = new Usuario("un usuario valido", "1contraseniaNoTaaaanSegura");
    	assertFalse(usuario.validarContrasenia());
    }
}
