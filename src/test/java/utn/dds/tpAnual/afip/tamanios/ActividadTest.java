package utn.dds.tpAnual.afip.tamanios;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import utn.dds.tpAnual.afip.Actividad;
import utn.dds.tpAnual.afip.RequisitoSectorEmpresa;
import utn.dds.tpAnual.afip.Sector;
import utn.dds.tpAnual.usuario.Usuario;

public class ActividadTest {
	private RequisitoSectorEmpresa requisito1 = new RequisitoSectorEmpresa(100, 200F, Micro.getInstance());
	private RequisitoSectorEmpresa requisito2 = new RequisitoSectorEmpresa(500, 800F, Pequenia.getInstance());
	private RequisitoSectorEmpresa requisito3 = new RequisitoSectorEmpresa(10000, 800F, MedianaTramo1.getInstance());
	private RequisitoSectorEmpresa requisito4 = new RequisitoSectorEmpresa(100000, 8000F, MedianaTramo2.getInstance());
	private Sector sectorConRequisitosOrdenados = new Sector("Financiero", Arrays.asList(requisito4, requisito3, requisito2, requisito1));
	private Actividad actividad = new Actividad("Prestamista", sectorConRequisitosOrdenados);
	
    @Test
    public void empresaValida() {
    	assertTrue(actividad.getTamanioEmpresaPara(1000, 500F).equals(MedianaTramo1.getInstance()));
    }
    
    @Test
    public void empresaExcede() {
    	boolean hasError = false;
    	try {
    		actividad.getTamanioEmpresaPara(1000000, 500F);
    	}catch(Exception e) {
    		hasError = true;
    	}
    	assertTrue(hasError);
    }
    
}
