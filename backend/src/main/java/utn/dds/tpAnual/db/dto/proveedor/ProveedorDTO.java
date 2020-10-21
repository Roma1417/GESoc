package utn.dds.tpAnual.db.dto.proveedor;

import javassist.expr.Instanceof;
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
    public ProveedorDTO from(Proveedor object) {
        loadTipoProveedor(object);
        ProveedorDTO proveedor = new ProveedorDTO();
        if(tipoProveedor.equals(TipoProveedor.JURIDICO)){
            setDatosProveedorJuridico((ProveedorJuridico) object, proveedor);
        }
        else {
            setDatosProveedorPersona((ProveedorPersona) object, proveedor);
        }
        return proveedor;
    }

    private void setDatosProveedorPersona(ProveedorPersona proveedor, ProveedorDTO proveedorDto) {
        dni =  proveedor.getDni();
        razonSocial = proveedor.getNombreRazonSocial();
        idProveedor = proveedor.getProveedorId();
    }

    private void setDatosProveedorJuridico(ProveedorJuridico proveedor, ProveedorDTO proveedorDto) {
        CUIT =  proveedor.getCUIT();
        razonSocial = proveedor.getNombreRazonSocial();
        idProveedor = proveedor.getProveedorId();
    }

    private void loadTipoProveedor(Proveedor object) {
        this.tipoProveedor = object instanceof ProveedorJuridico ?
                TipoProveedor.JURIDICO : TipoProveedor.PERSONA;
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
