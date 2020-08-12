package utn.dds.tpAnual.afip.tamanios;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.afip.RequisitoSectorEmpresa;
import utn.dds.tpAnual.db.entity.afip.Sector;
import utn.dds.tpAnual.builders.ActividadBuilder;
import utn.dds.tpAnual.builders.RequisitoSectorEmpresaBuilder;
import utn.dds.tpAnual.builders.SectorBuilder;
import utn.dds.tpAnual.db.entity.afip.tamanios.MedianaTramo1;
import utn.dds.tpAnual.db.entity.afip.tamanios.MedianaTramo2;
import utn.dds.tpAnual.db.entity.afip.tamanios.Micro;
import utn.dds.tpAnual.db.entity.afip.tamanios.Pequenia;

public class ActividadTest {
	private RequisitoSectorEmpresa requisito1 = new RequisitoSectorEmpresaBuilder()
			.withMaximoEmpleados(100)
			.withMaximoFacturacion(200F)
			.withTamanioEmpresa(Micro.getInstance())
			.build();
	private RequisitoSectorEmpresa requisito2 = new RequisitoSectorEmpresaBuilder()
			.withMaximoEmpleados(500)
			.withMaximoFacturacion(800F)
			.withTamanioEmpresa(Pequenia.getInstance())
			.build();
	private RequisitoSectorEmpresa requisito3 = new RequisitoSectorEmpresaBuilder()
			.withMaximoEmpleados(10000)
			.withMaximoFacturacion(800F)
			.withTamanioEmpresa(MedianaTramo1.getInstance())
			.build();
	private RequisitoSectorEmpresa requisito4 = new RequisitoSectorEmpresaBuilder()
			.withMaximoEmpleados(100000)
			.withMaximoFacturacion(8000F)
			.withTamanioEmpresa(MedianaTramo2.getInstance())
			.build();
	
	private Sector sectorConRequisitosDesordenados = new SectorBuilder()
			.withNombre("Financiero")
			.withRequisitoSectorEmpresa(requisito4)
			.withRequisitoSectorEmpresa(requisito3)
			.withRequisitoSectorEmpresa(requisito1)
			.withRequisitoSectorEmpresa(requisito2).build();
	private Sector sectorConRequisitosMasDesordenados = new SectorBuilder()
			.withNombre("Indrustrial")
			.withRequisitoSectorEmpresa(requisito1)
			.withRequisitoSectorEmpresa(requisito2)
			.withRequisitoSectorEmpresa(requisito4)
			.withRequisitoSectorEmpresa(requisito3)
			.build();
	
	private Actividad unaActividad = new ActividadBuilder()
			.withNombre("Prestamista")
			.withSector(sectorConRequisitosDesordenados)
			.build();
	private Actividad otraActividad = new ActividadBuilder()
			.withNombre("Fabricacion de autos")
			.withSector(sectorConRequisitosMasDesordenados)
			.build();

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
