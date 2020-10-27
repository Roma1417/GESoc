package utn.dds.tpAnual.db.dto.ubicacion;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;

public class PaisDTO extends StandardDTO<Pais> {
    private Long paisId;
    private String idAPI;
    private String descripcion;

    public PaisDTO(){
    }

    public PaisDTO(String idAPI, String descripcion) {
        this.idAPI = idAPI;
        this.descripcion = descripcion;
    }

    @Override
    public PaisDTO from(Pais object) {
        PaisDTO paisDTO = new PaisDTO();
        paisDTO.setDescripcion(object.getDescripcion());
        paisDTO.setIdAPI(object.getIdAPI());
        paisDTO.setPaisId(object.getPaisId());
        return paisDTO;
    }

    @Override
    public Pais toEntity() {
        return null;
    }

    public Long getPaisId() {
        return paisId;
    }

    public void setPaisId(Long paisId) {
        this.paisId = paisId;
    }

    public String getIdAPI() {
        return idAPI;
    }

    public void setIdAPI(String idAPI) {
        this.idAPI = idAPI;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
