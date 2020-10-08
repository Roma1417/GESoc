package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.transaccion.EgresoDTO;
import utn.dds.tpAnual.db.dto.complex.VinculacionEgresoIngresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.service.jpaService.EgresoService;
import utn.dds.tpAnual.db.service.jpaService.IngresoService;
import utn.dds.tpAnual.db.service.rules.EgresoRules;

import java.util.Optional;

@Service
public class EgresoResourceBean {

    @Autowired
    private EgresoService egresoService;

    @Autowired
    private IngresoService ingresoService;

    public PageableResponse<EgresoDTO, Egreso> getEgresos(PageableRequest pageableRequest, String categoria) {
        return null;
    }

    public EgresoDTO crearEgresos(EgresoDTO egresoDTO){
        return null;
    }

    public void vincular(VinculacionEgresoIngresoDTO vinculacion) {
        Optional<Egreso> egresoOptional = egresoService.findFullById(vinculacion.getEgresoId());
        Optional<Ingreso> ingresoOptional = ingresoService.findFullById(vinculacion.getIngresoId());
        if (!egresoOptional.isPresent() || !ingresoOptional.isPresent()){
            throw new RuntimeException("Los registros no fueron encontrados");
        }
        Egreso egreso = egresoOptional.get();
        Ingreso ingreso = ingresoOptional.get();
        EgresoRules.getInstance().validarVinculacion(egreso, ingreso);
        ingreso.vincularEgreso(egreso);
        egresoService.save(egreso);
    }
}
