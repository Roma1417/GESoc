package utn.dds.tpAnual.db.dto.transaccion;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;

public class IngresoDTO extends StandardDTO<Ingreso> {

    @Override
    public StandardDTO from(Ingreso object){
        // paso de uno a otro
        return null;
    }

    @Override
    public Ingreso toEntity() {
        return null;
    }
}
