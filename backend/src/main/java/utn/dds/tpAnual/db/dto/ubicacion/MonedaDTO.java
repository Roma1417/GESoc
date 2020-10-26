package utn.dds.tpAnual.db.dto.ubicacion;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;

public class MonedaDTO extends StandardDTO<Moneda> {
    private Long monedaId;
    private String idAPI;
    private String descripcion;
    private String simbolo;

    @Override
    public MonedaDTO from(Moneda object) {
        MonedaDTO monedaDTO = new MonedaDTO();
        monedaDTO.setDescripcion(object.getDescripcion());
        monedaDTO.setIdAPI(object.getIdAPI());
        monedaDTO.setMonedaId(object.getMonedaId());
        monedaDTO.setSimbolo(object.getSimbolo());
        return monedaDTO;
    }

    @Override
    public Moneda toEntity() {
        return null;
    }

    public Long getMonedaId() {
        return monedaId;
    }

    public void setMonedaId(Long monedaId) {
        this.monedaId = monedaId;
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

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
}
