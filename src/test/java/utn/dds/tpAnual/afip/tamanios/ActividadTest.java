package utn.dds.tpAnual.afip.tamanios;


import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import utn.dds.tpAnual.afip.Actividad;
import utn.dds.tpAnual.afip.RequisitoSectorEmpresa;
import utn.dds.tpAnual.afip.Sector;

public class ActividadTest {
	private RequisitoSectorEmpresa requisito1 = new RequisitoSectorEmpresa(100, 200F, Micro.getInstance());
	private RequisitoSectorEmpresa requisito2 = new RequisitoSectorEmpresa(500, 800F, Pequenia.getInstance());
	private RequisitoSectorEmpresa requisito3 = new RequisitoSectorEmpresa(10000, 800F, MedianaTramo1.getInstance());
	private RequisitoSectorEmpresa requisito4 = new RequisitoSectorEmpresa(100000, 8000F, MedianaTramo2.getInstance());
	private Sector sectorConRequisitosDesordenados = new Sector("Financiero", Arrays.asList(requisito4, requisito3, requisito1, requisito2));
	private Actividad unaActividad = new Actividad("Prestamista", sectorConRequisitosDesordenados);
	private Sector sectorConRequisitosMasDesordenados = new Sector("Indrustrial", Arrays.asList(requisito1, requisito2, requisito4, requisito3));
	private Actividad otraActividad = new Actividad("Fabricacion de autos", sectorConRequisitosMasDesordenados);

	@Test
    public void empresaValida() {
    	assertTrue(unaActividad.getTamanioEmpresaPara(1000, 500F).equals(MedianaTramo1.getInstance()));
    }

	@Test
	public void empresaConOtroTamanio() {
		assertTrue(otraActividad.getTamanioEmpresaPara(501, 500F).equals(MedianaTramo1.getInstance()));
	}
    
    @Test
    public void empresaExcede() {
    	boolean hasError = false;
    	try {
			unaActividad.getTamanioEmpresaPara(1000000, 500F);
    	}catch(Exception e) {
    		hasError = true;
    	}
    	assertTrue(hasError);
    }
    
}
