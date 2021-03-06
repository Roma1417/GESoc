package utn.dds.tpAnual.db.dto.transaccion;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.dto.entidad.EntidadDTO;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;

import java.time.LocalDate;


public class IngresoDTO extends StandardDTO<Ingreso> {

    private Long idIngreso;
    private Float total;
    private String descripcion;
    private DocumentoComercialDTO documentoComercial;
    private EntidadDTO entidadRealizadora;
    private Integer codigoOperacion;

    @Override
    public Ingreso toEntity() {
        Ingreso ingreso = new Ingreso();
        ingreso.setDescripcion(descripcion);
        ingreso.setTotal(total);
        ingreso.setFecha(LocalDate.now());
        ingreso.setCodigoOperacion(codigoOperacion);
        return ingreso;
    }

    @Override
    public IngresoDTO from(Ingreso object){
        IngresoDTO ingresoDto = new IngresoDTO();
        ingresoDto.setIdIngreso(object.getOperacionId());
        ingresoDto.setTotal(object.getTotal());
        ingresoDto.setCodigoOperacion(object.getCodigoOperacion());
        ingresoDto.setDescripcion(object.getDescripcion());
        ingresoDto.setEntidadRealizadora(new EntidadDTO().from(object.getEntidadRealizadora()));
        ingresoDto.setDocumentoComercial(new DocumentoComercialDTO().from(object.getDocumentoComercial()));
        return ingresoDto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Long getIdIngreso() { return idIngreso; }

    public void setIdIngreso(Long idIngreso) { this.idIngreso = idIngreso;}

    public Float getTotal() { return total; }

    public void setTotal(Float total) { this.total = total; }

}
