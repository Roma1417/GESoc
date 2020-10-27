package utn.dds.tpAnual.builders;

import utn.dds.tpAnual.db.dto.categoria.CategoriaDTO;
import utn.dds.tpAnual.db.dto.entidad.EntidadDTO;
import utn.dds.tpAnual.db.dto.transaccion.*;
import utn.dds.tpAnual.db.dto.ubicacion.MonedaDTO;
import utn.dds.tpAnual.db.dto.ubicacion.PaisDTO;
import utn.dds.tpAnual.db.entity.afip.VentaAnual;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PresupuestoDTOBuilder {
	private List<DetallePrecioDTO> detallesPrecio = new ArrayList<>();
	private DocumentoComercialDTO documentoComercial;
	private EntidadDTO entidadRealizadora;
    private Long operacionId;
    private Integer codigoOperacion;
    private LocalDate fechaCreacion;
    private Long egresoID;
    private Float total;
	
	public PresupuestoDTOBuilder withDetallePrecio(DetallePrecioDTO detallePrecio){
        this.detallesPrecio.add(detallePrecio);
        return this;
    }
	
	public PresupuestoDTOBuilder withListaDetallesPrecio(List<DetallePrecioDTO> detallesPrecio){
        this.detallesPrecio = detallesPrecio;
        return this;
    }
	
	public PresupuestoDTOBuilder withDocumentoComercial(DocumentoComercialDTO documentoComercial){
        this.documentoComercial = documentoComercial;
        return this;
    }
	
    public PresupuestoDTOBuilder withEntidadRealizadora(EntidadDTO entidadRealizadora){
        this.entidadRealizadora = entidadRealizadora;
        return this;
    }
    
	public PresupuestoDTOBuilder withCodigoOperacion(int codigoOperacion){
        this.codigoOperacion = codigoOperacion;
        return this;
    }

    public PresupuestoDTO build(){
        PresupuestoDTO presupuesto = new PresupuestoDTO();
        presupuesto.setDocumentoComercial(documentoComercial);
        presupuesto.setEntidadRealizadora(entidadRealizadora);
        presupuesto.setCodigoOperacion(codigoOperacion);
        presupuesto.setDetallesPrecio(detallesPrecio);
        presupuesto.setTotal(total);
        presupuesto.setFechaCreacion(fechaCreacion);
        return presupuesto;
    }

    public PresupuestoDTO buildPresupuestoDTOSinDetallesEItems(){
        codigoOperacion = 522;
        documentoComercial = new DocumentoComercialDTO();
        documentoComercial.setNumero(8765423);
        documentoComercial.setTipoDocumento(7);
        fechaCreacion = LocalDate.now();
        total = 1000F;
        return build();
    }
}
