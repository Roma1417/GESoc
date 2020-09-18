package utn.dds.tpAnual.db.service.vinculacion.vinculador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.service.EgresoService;
import utn.dds.tpAnual.db.service.EntidadService;
import utn.dds.tpAnual.db.service.IngresoService;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.service.vinculacion.reglaVinculacion.ReglaFechaAceptable;
import utn.dds.tpAnual.db.service.vinculacion.reglaVinculacion.ReglaVinculacion;

import java.util.Arrays;
import java.util.List;

@Service
public class Vinculador {

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private EgresoService egresoService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private ReglaFechaAceptable reglaFechaAceptable;

    private List<ReglaVinculacion> getReglas() {
        return Arrays.asList(reglaFechaAceptable);
    }

    public void vincularSistema(){
        List<Entidad> entidadesAVincular = entidadService.findAll();
        entidadesAVincular.forEach(entidad -> {
            vincularEntidad(entidad);
        });
    }
    public void vincularEntidad(Entidad entidad){
        List<Egreso> egresos = egresoService.getEgresosVinculablesByEntidadRealizadora(entidad);
        List<Ingreso> ingresos = ingresoService.getIngresoVinculableByEntidadRealidadora(entidad);
        if (entidad.getCriterioVinculacion() != null) {
            entidad.getCriterioVinculacion().ejecutarAlgoritmoVinculacion(ingresos, egresos, getReglas());
            egresoService.saveAll(egresos);
            ingresoService.saveAll(ingresos);
        }
    }
}
