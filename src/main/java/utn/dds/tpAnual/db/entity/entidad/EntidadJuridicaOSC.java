package utn.dds.tpAnual.db.entity.entidad;

import javax.persistence.*;

/**
 * @author Daiana
 * @version 1.0
 * @created 10-abr.-2020 18:19:18
 */
@Entity
@Table(name = "ENTIDAD_JURIDICA_OSC")
public class EntidadJuridicaOSC extends EntidadJuridica {

	public EntidadJuridicaOSC(){

	}
	
	public EntidadJuridicaOSC(String nombre){
		super(nombre);
	}

}