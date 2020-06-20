package utn.dds.tpAnual.builders;

import utn.dds.tpAnual.afip.RequisitoSectorEmpresa;
import utn.dds.tpAnual.afip.tamanios.TamanioEmpresa;

public class RequisitoSectorEmpresaBuilder {
	private int maximoEmpleados;
	private Float maximoFacturacion;
	private TamanioEmpresa tamanioEmpresa;
	
	public RequisitoSectorEmpresaBuilder withMaximoEmpleados(int maximoEmpleados){
        this.maximoEmpleados = maximoEmpleados;
        return this;
    }
	public RequisitoSectorEmpresaBuilder withMaximoFacturacion(Float maximoFacturacion){
        this.maximoFacturacion = maximoFacturacion;
        return this;
    }
	public RequisitoSectorEmpresaBuilder withTamanioEmpresa(TamanioEmpresa tamanioEmpresa){
        this.tamanioEmpresa = tamanioEmpresa;
        return this;
    }

    public RequisitoSectorEmpresa build(){
        return new RequisitoSectorEmpresa(maximoEmpleados, maximoFacturacion, tamanioEmpresa);
    }
}
