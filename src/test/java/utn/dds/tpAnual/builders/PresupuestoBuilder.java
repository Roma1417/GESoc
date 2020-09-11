package utn.dds.tpAnual.builders;

import java.util.ArrayList;
import java.util.List;

import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.DetallePrecio;
import utn.dds.tpAnual.db.entity.transaccion.DocumentoComercial;
import utn.dds.tpAnual.db.entity.transaccion.Presupuesto;

public class PresupuestoBuilder {
	private List<DetallePrecio> detallesPrecio = new ArrayList<DetallePrecio>();
	private DocumentoComercial documentoComercial;
	private Entidad entidadRealizadora;
	private int codigoOperacion;
	
	public PresupuestoBuilder withDetallePrecio(DetallePrecio detallePrecio){
        this.detallesPrecio.add(detallePrecio);
        return this;
    }
	
	public PresupuestoBuilder withListaDetallesPrecio(List<DetallePrecio> detallesPrecio){
        this.detallesPrecio = detallesPrecio;
        return this;
    }
	
	public PresupuestoBuilder withDocumentoComercial(DocumentoComercial documentoComercial){
        this.documentoComercial = documentoComercial;
        return this;
    }
	
    public PresupuestoBuilder withEntidadRealizadora(Entidad entidadRealizadora){
        this.entidadRealizadora = entidadRealizadora;
        return this;
    }
    
	public PresupuestoBuilder withCodigoOperacion(int codigoOperacion){
        this.codigoOperacion = codigoOperacion;
        return this;
    }

    public Presupuesto build(){
        return new Presupuesto(documentoComercial, entidadRealizadora, codigoOperacion, detallesPrecio);
    }
}
