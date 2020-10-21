package utn.dds.tpAnual.db.dto.ubicacion;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;

public class PaisDTO extends StandardDTO<Pais> {
    private Long idPais;

    @Override
    public StandardDTO from(Pais object) {
        return null;
    }

    @Override
    public Pais toEntity() {
        return null;
    }

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }
}
