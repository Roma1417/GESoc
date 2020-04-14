package utn.dds.tpAnual.entidad;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utn.dds.tpAnual.afip.VentaAnual;
import utn.dds.tpAnual.usuario.Usuario;

public class EntidadJuridicaEmpresaTest {

   
    @Test
    public void totalVentasAnuales() {
    	EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresa();
    	VentaAnual v1 = new VentaAnual(2020, 100001.5F);
    	VentaAnual v2 = new VentaAnual(2019, 50001.0F);
    	VentaAnual v3 = new VentaAnual(2018, 50001.0F);
    	VentaAnual v4 = new VentaAnual(2017, 50001.0F);
    	VentaAnual v5 = new VentaAnual(2016, 40001.0F);
    	
    	entidad.addVentaAnual(v1);
    	entidad.addVentaAnual(v2);
    	entidad.addVentaAnual(v3);
    	entidad.addVentaAnual(v4);
    	entidad.addVentaAnual(v5);
    	
    	assertTrue(entidad.getPromedioVentasParaAnios(3).equals(250004.5F/3));
    }
    
    @Test
    public void totalVentasAnualesSiNoHayVentas() {
    	EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresa();
    	
    	assertTrue(entidad.getPromedioVentasParaAnios(3).equals(0F));
    }
    
    public void totalVentasAnualesSiSonTodasViejas() {
    	EntidadJuridicaEmpresa entidad = new EntidadJuridicaEmpresa();
    	
    	VentaAnual v1 = new VentaAnual(2014, 100001.5F);
    	VentaAnual v2 = new VentaAnual(2016, 50001.0F);
    	entidad.addVentaAnual(v1);
    	entidad.addVentaAnual(v2);
    	
    	assertTrue(entidad.getPromedioVentasParaAnios(3).equals(0F));
    }
    
}
