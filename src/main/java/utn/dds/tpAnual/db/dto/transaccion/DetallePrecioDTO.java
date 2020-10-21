package utn.dds.tpAnual.db.dto.transaccion;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.DetallePrecio;

public class DetallePrecioDTO extends StandardDTO<DetallePrecio> {
    private Long detallePrecioId;
    private Float precio;
    private DetalleOperacion detalleOperacion;

    public DetallePrecio toEntity(DetalleOperacion detalleOperacionRecibido) {
        DetallePrecio detallePrecio = new DetallePrecio();
        detallePrecio.setPrecio(precio);
        detallePrecio.setDetalleOperacion(detalleOperacionRecibido);
        detallePrecio.setDetallePrecioId(detallePrecioId);
        return detallePrecio;
    }
    @Override
    public DetallePrecioDTO from(DetallePrecio object){
        DetallePrecioDTO detallePrecioDTO = new DetallePrecioDTO();
        detallePrecioDTO.setPrecio(object.getPrecio());
        detallePrecioDTO.setDetallePrecioId(object.getDetallePrecioId());
        detallePrecioDTO.setDetalleOperacion(object.getDetalleOperacion());
        return detallePrecioDTO;
    }

    @Override
    public DetallePrecio toEntity() { return null; }

    public Long getDetallePrecioId() { return detallePrecioId; }

    public void setDetallePrecioId(Long detallePrecioId) { this.detallePrecioId = detallePrecioId; }

    public Float getPrecio() { return precio; }

    public void setPrecio(Float precio) { this.precio = precio; }

    public DetalleOperacion getDetalleOperacion() { return detalleOperacion; }

    public void setDetalleOperacion(DetalleOperacion detalleOperacion) { this.detalleOperacion = detalleOperacion; }

}
