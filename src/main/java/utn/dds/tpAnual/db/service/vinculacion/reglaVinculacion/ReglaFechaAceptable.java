package utn.dds.tpAnual.db.service.vinculacion.reglaVinculacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.configuracion.ConfiguracionEnum;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.service.ConfiguracionService;

import java.time.temporal.ChronoUnit;


@Service
public class ReglaFechaAceptable extends ReglaVinculacion{

    private static  final ReglaFechaAceptable instance = new ReglaFechaAceptable();

    @Autowired
    private ConfiguracionService configuracionService;

    private ReglaFechaAceptable(){

    }

    public static ReglaFechaAceptable getInstance(){
        return instance;
    }

    @Override
    public boolean puedeVincularse(Ingreso ingreso, Egreso egreso) {
        if (ingreso.getFecha() != null && egreso.getFecha() != null){
            Long diferenciaEnDias = ingreso.getFecha().until(egreso.getFecha(), ChronoUnit.DAYS);
            if (diferenciaEnDias < 0L) {
                diferenciaEnDias *= -1;
            }
            return Integer.toUnsignedLong(configuracionService.getIntValue(ConfiguracionEnum.DIAS_ACEPTABLE_VINCULACION))
                    >= diferenciaEnDias;
        } else {
            return false;
        }

    }
}
