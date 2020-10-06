package utn.dds.tpAnual.db.dto;

import utn.dds.tpAnual.db.entity.transaccion.Egreso;

public class EgresoDTO extends StandardDTO<Egreso>{

    @Override
    public StandardDTO from(Egreso object){
        // paso de uno a otro
        return null;
    }

    @Override
    public Egreso toEntity() {
        return null;
    }
}
