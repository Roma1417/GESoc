package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.EgresoDTO;
import utn.dds.tpAnual.db.dto.IngresoDTO;
import utn.dds.tpAnual.db.dto.complex.VinculacionEgresoIngresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.service.jpaService.EgresoService;
import utn.dds.tpAnual.db.service.jpaService.IngresoService;

@Service
public class IngresoResourceBean {

    @Autowired
    private IngresoService ingresoService;

    public PageableResponse<IngresoDTO, Ingreso> getIngresos(PageableRequest pageableRequest, String categoria) {
        return null;
    }

    public EgresoDTO crearIngreso(IngresoDTO ingresoDTO){
        return null;
    }

}
