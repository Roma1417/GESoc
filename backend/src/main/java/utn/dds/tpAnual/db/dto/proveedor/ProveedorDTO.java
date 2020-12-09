package utn.dds.tpAnual.db.dto.proveedor;

import javassist.expr.Instanceof;
import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.proveedor.ProveedorJuridico;
import utn.dds.tpAnual.db.entity.proveedor.ProveedorPersona;

public class ProveedorDTO extends StandardDTO<Proveedor> {
    private Long idProveedor;
    private String CUIT;
    private String dni;
    private String nombreRazonSocial;
    private TipoProveedor tipoProveedor;

    public ProveedorDTO(){

    }

    @Override
    public ProveedorDTO from(Proveedor object) {
        if (object == null) {
            return null;
        }
        ProveedorDTO proveedor = new ProveedorDTO();
        loadTipoProveedor(object, proveedor);
        if(proveedor.getTipoProveedor().equals(TipoProveedor.JURIDICO)){
            setDatosProveedorJuridico((ProveedorJuridico) object, proveedor);
        }
        else if (proveedor.getTipoProveedor().equals(TipoProveedor.PERSONA)) {
            setDatosProveedorPersona((ProveedorPersona) object, proveedor);
        }
        proveedor.setNombreRazonSocial(object.getNombreRazonSocial());
        proveedor.setIdProveedor(object.getProveedorId());
        return proveedor;
    }

    private void setDatosProveedorPersona(ProveedorPersona proveedor, ProveedorDTO proveedorDto) {
        proveedorDto.setDni(proveedor.getDni());
    }

    private void setDatosProveedorJuridico(ProveedorJuridico proveedor, ProveedorDTO proveedorDto) {
        proveedorDto.setCUIT(proveedor.getCUIT());
    }

    private void loadTipoProveedor(Proveedor object, ProveedorDTO proveedorDTO) {
        proveedorDTO.setTipoProveedor(object instanceof ProveedorJuridico ?
                TipoProveedor.JURIDICO : TipoProveedor.PERSONA);
    }

    @Override
    public Proveedor toEntity() {
        if (TipoProveedor.JURIDICO.equals(tipoProveedor)){
            return new ProveedorJuridico(null, CUIT, nombreRazonSocial);
        } else if (TipoProveedor.PERSONA.equals(tipoProveedor)){
            return new ProveedorPersona(null, dni, nombreRazonSocial);
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

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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
