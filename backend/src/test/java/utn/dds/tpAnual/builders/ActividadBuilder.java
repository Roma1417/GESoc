package utn.dds.tpAnual.builders;

import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.afip.Sector;

public class ActividadBuilder {
	private String nombreActividad;
	private Sector sector;
	
	public ActividadBuilder withNombre(String nombreActividad){
        this.nombreActividad = nombreActividad;
        return this;
    }
	public ActividadBuilder withSector(Sector sector){
        this.sector = sector;
        return this;
    }
    public Actividad build(){
        return new Actividad(nombreActividad, sector);
    }
}
