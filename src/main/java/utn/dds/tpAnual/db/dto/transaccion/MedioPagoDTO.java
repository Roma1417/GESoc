package utn.dds.tpAnual.db.dto.transaccion;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.MedioPago;

public class MedioPagoDTO extends StandardDTO<MedioPago> {
    private Long idMedioPago;
    private String instrumentoPago;

    @Override
    public StandardDTO from(MedioPago object) {
        return null;
    }

    @Override
    public MedioPago toEntity() {
        return new MedioPago(instrumentoPago);
    }

    public Long getIdMedioPago() {
        return idMedioPago;
    }

    public void setIdMedioPago(Long idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    public String getInstrumentoPago() {
        return instrumentoPago;
    }

    public void setInstrumentoPago(String instrumentoPago) {
        this.instrumentoPago = instrumentoPago;
    }
}
