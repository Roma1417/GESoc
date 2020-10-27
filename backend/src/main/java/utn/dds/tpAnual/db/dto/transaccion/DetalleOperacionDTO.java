package utn.dds.tpAnual.db.dto.transaccion;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;

import java.util.List;
import java.util.stream.Collectors;

public class DetalleOperacionDTO  extends StandardDTO<DetalleOperacion> {

    private Long detalleOperacionId;
    private ItemDTO item;
    private Float precio;
    private Integer cantidad;

    @Override
    public DetalleOperacionDTO from(DetalleOperacion object) {
        return null;
    }

    @Override
    public DetalleOperacion toEntity() {
        return new DetalleOperacion(null, precio, cantidad);
    }

    public static List<DetalleOperacion> toListStatic(List<DetalleOperacionDTO> dtoList){
        return dtoList.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
    }

    public DetalleOperacionDTO(){

    }

    public DetalleOperacionDTO(ItemDTO item, Float precio, Integer cantidad) {
        this.item = item;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public DetalleOperacionDTO(ItemDTO item, Float precio, Integer cantidad, Long detalleOperacionId ) {
        this.item = item;
        this.precio = precio;
        this.cantidad = cantidad;
        this.detalleOperacionId = detalleOperacionId;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getDetalleOperacionId() {
        return detalleOperacionId;
    }

    public void setDetalleOperacionId(Long detalleOperacionId) {
        this.detalleOperacionId = detalleOperacionId;
    }
}
