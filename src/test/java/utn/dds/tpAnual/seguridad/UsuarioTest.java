package utn.dds.tpAnual.seguridad;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UsuarioTest {


    @Test
    public void exampleTest() {
    	assertTrue(true);
    }
    
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
