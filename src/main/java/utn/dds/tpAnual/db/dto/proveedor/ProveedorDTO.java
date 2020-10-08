package utn.dds.tpAnual.db.dto.proveedor;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.proveedor.ProveedorJuridico;
import utn.dds.tpAnual.db.entity.proveedor.ProveedorPersona;

public class ProveedorDTO extends StandardDTO<Proveedor> {
    private Long idProveedor;
    private String CUIT;
    private String razonSocial;
    private String dni;
    private String nombre;
    private TipoProveedor tipoProveedor;

    public ProveedorDTO(){

    }

    @Override
    public StandardDTO from(Proveedor object) {
        return null;
    }

    @Override
    public Proveedor toEntity() {
        if (TipoProveedor.JURIDICO.equals(tipoProveedor)){
            return new ProveedorJuridico(null, CUIT, razonSocial);
        } else if (TipoProveedor.PERSONA.equals(tipoProveedor)){
            return new ProveedorPersona(null, dni, nombre);
        } else {
            throw new RuntimeException("Debe especificar el tipo de proveedor");
        }
    }

    public String getCUIT() {
        return CUIT;
    }

    public void setCUIT(String CUIT) {
        this.CUIT = CUIT;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoProveedor getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(TipoProveedor tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }
}
