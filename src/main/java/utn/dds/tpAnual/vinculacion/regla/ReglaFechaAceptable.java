package utn.dds.tpAnual.vinculacion.regla;

import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;


@Service
public class ReglaFechaAceptable extends ReglaVinculacion{

    private static  final ReglaFechaAceptable instance = new ReglaFechaAceptable();

    private ReglaFechaAceptable(){

    }

    public static ReglaFechaAceptable getInstance(){
        return instance;
    }

    @Override
    public boolean puedeVincularse(Ingreso ingreso, Egreso egreso) {
        return false;
    }
}
