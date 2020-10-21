package utn.dds.tpAnual.db.dto.ubicacion;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;

public class MonedaDTO extends StandardDTO<Moneda> {
    private Long idMoneda;

    public Long getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Long idMoneda) {
        this.idMoneda = idMoneda;
    }

    @Override
    public StandardDTO from(Moneda object) {
        return null;
    }

    @Override
    public Moneda toEntity() {
        return null;
    }
}
