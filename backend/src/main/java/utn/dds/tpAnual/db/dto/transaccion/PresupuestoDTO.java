package utn.dds.tpAnual.db.dto.transaccion;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.transaccion.DetallePrecio;
import utn.dds.tpAnual.db.entity.transaccion.Presupuesto;

import java.util.List;

public class PresupuestoDTO extends StandardDTO<Presupuesto> {
    private List<DetallePrecio> detallesPrecio;

    @Override
    public Presupuesto toEntity() {
        //falta ver realacionar con el toEntity
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setDetallesPrecio(detallesPrecio);
        return presupuesto;
    }
    @Override
    public PresupuestoDTO from(Presupuesto object){
        //falta ver realacionar con el from de detalle
        PresupuestoDTO presupuestoDTO = new PresupuestoDTO();
        presupuestoDTO.setDetallesPrecio(object.getDetallesPrecio());
        return presupuestoDTO;
    }

    public List<DetallePrecio> getDetallesPrecio() { return detallesPrecio; }

    public void setDetallesPrecio(List<DetallePrecio> detallesPrecio) { this.detallesPrecio = detallesPrecio; }

}
