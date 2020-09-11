package utn.dds.tpAnual.db.entity.entidad;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utn.dds.tpAnual.db.entity.afip.VentaAnual;
import utn.dds.tpAnual.builders.EntidadJuridicaEmpresaBuilder;

public class EntidadJuridicaEmpresaTest {

   
    @Test
    public void totalVentasAnuales() {
    	EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder()
    			.withVentaAnual(new VentaAnual(2020, 100001.5F))
    			.withVentaAnual(new VentaAnual(2019, 50001.0F))
    			.withVentaAnual(new VentaAnual(2018, 50001.0F))
    			.withVentaAnual(new VentaAnual(2017, 50001.0F))
    			.withVentaAnual(new VentaAnual(2016, 40001.0F))
    			.build();

    	assertTrue(entidad.getPromedioVentasParaAnios(3).equals(250004.5F/3));
    }
    
    @Test
    public void totalVentasAnualesSiNoHayVentas() {
    	EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresa();
    	
    	assertTrue(entidad.getPromedioVentasParaAnios(3).equals(0F));
    }
    
    public void totalVentasAnualesSiSonTodasViejas() {
    	EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresaBuilder()
    			.withVentaAnual(new VentaAnual(2014, 100001.5F))
    			.withVentaAnual(new VentaAnual(2016, 50001.0F))
    			.build();
    	
    	assertTrue(entidad.getPromedioVentasParaAnios(3).equals(0F));
    }
    
}
