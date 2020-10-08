package utn.dds.tpAnual.db.dto.transaccion;

import utn.dds.tpAnual.db.dto.*;
import utn.dds.tpAnual.db.dto.entidad.EntidadDTO;
import utn.dds.tpAnual.db.dto.proveedor.ProveedorDTO;
import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;

import java.time.LocalDate;
import java.util.List;

public class EgresoDTO extends StandardDTO<Egreso> {

    private EntidadDTO entidadRealizadora;
    private List<DetalleOperacionDTO> detalles;
    private ProveedorDTO proveedor;
    private MedioPagoDTO medioPago;
    private DocumentoComercialDTO documentoComercial;
    private Integer codigoOperacion;
    private LocalDate fechaOperacion;

    @Override
    public StandardDTO from(Egreso object){
        // paso de uno a otro
        return null;
    }

    @Override
    public Egreso toEntity() {
        return null;
    }

    public EntidadDTO getEntidadRealizadora() {
        return entidadRealizadora;
    }

    public void setEntidadRealizadora(EntidadDTO entidadRealizadora) {
        this.entidadRealizadora = entidadRealizadora;
    }

    public List<DetalleOperacionDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOperacionDTO> detalles) {
        this.detalles = detalles;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    public MedioPagoDTO getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPagoDTO medioPago) {
        this.medioPago = medioPago;
    }

    public DocumentoComercialDTO getDocumentoComercial() {
        return documentoComercial;
    }

    public void setDocumentoComercial(DocumentoComercialDTO documentoComercial) {
        this.documentoComercial = documentoComercial;
    }

    public Integer getCodigoOperacion() {
        return codigoOperacion;
    }

    public void setCodigoOperacion(Integer codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(LocalDate fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }
}
