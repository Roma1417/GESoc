package utn.dds.tpAnual.db.dto.auditoria;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.mongo.auditoria.TipoOperacion;
import utn.dds.tpAnual.db.mongo.entity.RegistroOperacion;

import java.time.LocalDateTime;

public class RegistroOperacionDTO   extends StandardDTO<RegistroOperacion> {
    private LocalDateTime fechaOperacion;
    private TipoOperacion tipoOperacion;
    private Object objetoModificado;
    private String nombreClase;


    public RegistroOperacionDTO(){

    }

    public RegistroOperacionDTO(LocalDateTime fechaOperacion, TipoOperacion tipoOperacion, Object objetoModificado, String nombreClase) {
        this.fechaOperacion = fechaOperacion;
        this.tipoOperacion = tipoOperacion;
        this.objetoModificado = objetoModificado;
        this.nombreClase = nombreClase;
    }

    public LocalDateTime getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(LocalDateTime fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Object getObjetoModificado() {
        return objetoModificado;
    }

    public void setObjetoModificado(Object objetoModificado) {
        this.objetoModificado = objetoModificado;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    @Override
    public StandardDTO from(RegistroOperacion object) {
        return new RegistroOperacionDTO(object.getFechaOperacion(), object.getTipoOperacion(), object.getObjetoModificado(),
                object.getNombreClase());
    }

    @Override
    public RegistroOperacion toEntity() {
        return null;
    }
}
