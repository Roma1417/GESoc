package utn.dds.tpAnual.db.dto.transaccion;

import utn.dds.tpAnual.db.dto.*;
import utn.dds.tpAnual.db.dto.entidad.EntidadDTO;
import utn.dds.tpAnual.db.dto.proveedor.ProveedorDTO;
import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EgresoDTO extends StandardDTO<Egreso> {
    private Long idEgreso;
    private EntidadDTO entidadRealizadora;
    private List<DetalleOperacionDTO> detalles;
    private List<PresupuestoDTO> presupuestos;
    private ProveedorDTO proveedor;
    private MedioPagoDTO medioPago;
    private DocumentoComercialDTO documentoComercial;
    private Integer codigoOperacion;
    private Integer cantidadPresupuestosMinimos;
    private LocalDate fechaOperacion;
    private LocalDate fechaCreacion;
    private Float total;

    @Override
    public EgresoDTO from(Egreso object){
        EgresoDTO egresoDTO = new EgresoDTO();
        egresoDTO.setDetalles(object.getDetallesOperacion()
                .stream()
                .map(detalleOperacion ->  new DetalleOperacionDTO().from(detalleOperacion))
                .collect(Collectors.toList()));
        egresoDTO.setTotal(object.getTotal());
        egresoDTO.setProveedor(new ProveedorDTO().from(object.getProveedor()));
        egresoDTO.setFechaOperacion(object.getFechaOperacion());
        egresoDTO.setCantidadPresupuestosMinimos(object.getCantidadPresupuestosMinimos());
        egresoDTO.setCodigoOperacion(object.getCodigoOperacion());
        egresoDTO.setFechaCreacion(object.getFecha());
        return egresoDTO;
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

    public Integer getCantidadPresupuestosMinimos() {
        return cantidadPresupuestosMinimos;
    }

    public void setCantidadPresupuestosMinimos(Integer cantidadPresupuestosMinimos) {
        this.cantidadPresupuestosMinimos = cantidadPresupuestosMinimos;
    }

    public List<PresupuestoDTO> getPresupuestos() {
        return presupuestos;
    }

    public void setPresupuestos(List<PresupuestoDTO> presupuestos) {
        this.presupuestos = presupuestos;
    }

    public Long getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(Long idEgreso) {
        this.idEgreso = idEgreso;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
