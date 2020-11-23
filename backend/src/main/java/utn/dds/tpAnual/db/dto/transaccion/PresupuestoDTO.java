package utn.dds.tpAnual.db.dto.transaccion;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.dto.entidad.EntidadDTO;
import utn.dds.tpAnual.db.entity.transaccion.DetallePrecio;
import utn.dds.tpAnual.db.entity.transaccion.DocumentoComercial;
import utn.dds.tpAnual.db.entity.transaccion.Presupuesto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PresupuestoDTO extends StandardDTO<Presupuesto> {
    private List<DetallePrecioDTO> detallesPrecio;
    private Long operacionId;
    private DocumentoComercialDTO documentoComercial;
    private EntidadDTO entidadRealizadora;
    private Integer codigoOperacion;
    private LocalDate fechaCreacion;
    private Long egresoID;

    private Float total;

    @Override
    public Presupuesto toEntity() {
        //falta ver realacionar con el toEntity
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setDetallesPrecio(null);
        return presupuesto;
    }

    @Override
    public PresupuestoDTO from(Presupuesto object){
        PresupuestoDTO presupuestoDTO = new PresupuestoDTO();
        presupuestoDTO.setDetallesPrecio(object.getDetallesPrecio()
                .stream()
                .map(detallePrecio ->  new DetallePrecioDTO().from(detallePrecio))
                .collect(Collectors.toList()));
        presupuestoDTO.setCodigoOperacion(object.getCodigoOperacion());
        presupuestoDTO.setOperacionId(object.getOperacionId());
        presupuestoDTO.setFechaCreacion(object.getFecha());
        presupuestoDTO.setDocumentoComercial(new DocumentoComercialDTO().from(object.getDocumentoComercial()));
        presupuestoDTO.setTotal(object.getTotal());
        if (object.getEntidadRealizadora() != null) {
            presupuestoDTO.setEntidadRealizadora(new EntidadDTO().from(object.getEntidadRealizadora()));
        }
        presupuestoDTO.setEgresoID(object.getEgreso().getOperacionId());
        return presupuestoDTO;
    }

    public PresupuestoDTO() {
    }

    public Long getOperacionId() {
        return operacionId;
    }

    public void setOperacionId(Long operacionId) {
        this.operacionId = operacionId;
    }

    public DocumentoComercialDTO getDocumentoComercial() {
        return documentoComercial;
    }

    public void setDocumentoComercial(DocumentoComercialDTO documentoComercial) {
        this.documentoComercial = documentoComercial;
    }

    public EntidadDTO getEntidadRealizadora() {
        return entidadRealizadora;
    }

    public void setEntidadRealizadora(EntidadDTO entidadRealizadora) {
        this.entidadRealizadora = entidadRealizadora;
    }

    public Integer getCodigoOperacion() {
        return codigoOperacion;
    }

    public void setCodigoOperacion(Integer codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<DetallePrecioDTO> getDetallesPrecio() {
        return detallesPrecio;
    }

    public void setDetallesPrecio(List<DetallePrecioDTO> detallesPrecio) {
        this.detallesPrecio = detallesPrecio;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Long getEgresoID() {
        return egresoID;
    }

    public void setEgresoID(Long egresoID) {
        this.egresoID = egresoID;
    }
}
