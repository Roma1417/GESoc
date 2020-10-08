package utn.dds.tpAnual.db.dto.entidad;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.entidad.*;

public class EntidadDTO  extends StandardDTO<Entidad> {
    private Long idEntidad;
    private String nombre;
    private String descripcion;
    private Long codigoIGJ;
    private String cuit;
    private String razonSocial;
    private EntidadTipo entidadTipo;
    private Integer cantidadPersonal;

    public EntidadDTO (){

    }

    @Override
    public StandardDTO from(Entidad object) {
        return null;
    }

    @Override
    public Entidad toEntity() {
        if (EntidadTipo.BASE.equals(entidadTipo)){
            return new EntidadBase(nombre, descripcion);
        } else if (EntidadTipo.JURIDICA_OSC.equals(entidadTipo)) {
            EntidadJuridica entidadJuridica = new EntidadJuridicaOSC();
            entidadJuridica.setCodigoIGJ(codigoIGJ);
            entidadJuridica.setCUIT(cuit);
            entidadJuridica.setRazonSocial(razonSocial);
            entidadJuridica.setNombre(nombre);
            return entidadJuridica;
        } else if (EntidadTipo.JURIDICA_EMPRESA.equals(entidadTipo)){
            EntidadJuridicaEmpresa entidadJuridicaEmpresa = new EntidadJuridicaEmpresa();
            entidadJuridicaEmpresa.setCodigoIGJ(codigoIGJ);
            entidadJuridicaEmpresa.setCUIT(cuit);
            entidadJuridicaEmpresa.setRazonSocial(razonSocial);
            entidadJuridicaEmpresa.setNombre(nombre);
            entidadJuridicaEmpresa.setCantidadPersonal(cantidadPersonal);
            return entidadJuridicaEmpresa;
        } else {
            throw new RuntimeException("Se debe especificar el tipo de la entidad");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCodigoIGJ() {
        return codigoIGJ;
    }

    public void setCodigoIGJ(Long codigoIGJ) {
        this.codigoIGJ = codigoIGJ;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public EntidadTipo getEntidadTipo() {
        return entidadTipo;
    }

    public void setEntidadTipo(EntidadTipo entidadTipo) {
        this.entidadTipo = entidadTipo;
    }

    public Integer getCantidadPersonal() {
        return cantidadPersonal;
    }

    public void setCantidadPersonal(Integer cantidadPersonal) {
        this.cantidadPersonal = cantidadPersonal;
    }

    public Long getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Long idEntidad) {
        this.idEntidad = idEntidad;
    }
}
