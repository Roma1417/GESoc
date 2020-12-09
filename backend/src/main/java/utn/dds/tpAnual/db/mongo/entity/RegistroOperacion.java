package utn.dds.tpAnual.db.mongo.entity;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.mongo.auditoria.TipoOperacion;

import java.time.LocalDateTime;

public class RegistroOperacion {
    private LocalDateTime fechaOperacion;
    private TipoOperacion tipoOperacion;
    private Object objetoModificado;
    private String nombreClase;

    public RegistroOperacion(){
    }

    public RegistroOperacion(LocalDateTime fechaOperacion, TipoOperacion tipoOperacion, Object objetoModificado, String nombreClase) {
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

}
