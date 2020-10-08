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
        List<DetalleOperacion> detallesOperacion = DetalleOperacionDTO.toListStatic(detalles);
        return null;
    }
}
