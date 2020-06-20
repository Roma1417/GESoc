package utn.dds.tpAnual.builders;

import java.util.ArrayList;
import java.util.List;

import utn.dds.tpAnual.afip.RequisitoSectorEmpresa;
import utn.dds.tpAnual.afip.Sector;

public class SectorBuilder {
	private String nombreSector;
	private List<RequisitoSectorEmpresa> requisitos = new ArrayList<RequisitoSectorEmpresa>();

	public SectorBuilder withNombre(String nombreSector){
        this.nombreSector = nombreSector;
        return this;
    }
	public SectorBuilder withRequisitoSectorEmpresa(RequisitoSectorEmpresa requisito){
        this.requisitos.add(requisito);
        return this;
    }
    public Sector build(){
        return new Sector(nombreSector, requisitos);
    }
}
