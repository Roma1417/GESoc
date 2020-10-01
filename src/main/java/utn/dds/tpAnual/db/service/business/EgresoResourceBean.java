package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.EgresoDTO;
import utn.dds.tpAnual.db.dto.complex.VinculacionEgresoIngresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.service.jpaService.EgresoService;

@Service
public class EgresoResourceBean {

    @Autowired
    private EgresoService egresoService;

    public PageableResponse<EgresoDTO, Egreso> getEgresos(PageableRequest pageableRequest, String categoria) {
        return null;
    }

    public EgresoDTO crearEgresos(EgresoDTO egresoDTO){
        return null;
    }

    public void vincular(VinculacionEgresoIngresoDTO vinculacion) {
    }
}
