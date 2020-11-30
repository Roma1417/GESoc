package utn.dds.tpAnual.builders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import utn.dds.tpAnual.db.dto.transaccion.PresupuestoDTO;
import utn.dds.tpAnual.db.entity.afip.VentaAnual;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.entidad.EntidadBase;
import utn.dds.tpAnual.db.entity.transaccion.*;

public class PresupuestoBuilder {
	private List<DetallePrecio> detallesPrecio = new ArrayList<DetallePrecio>();
	private DocumentoComercial documentoComercial;
	private Entidad entidadRealizadora;
	private int codigoOperacion;
	private Egreso egreso;
	
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

    public PresupuestoBuilder withEgreso(Egreso egreso){
	    this.egreso = egreso;
	    return this;
    }

    public Presupuesto build(){
        Presupuesto presupuesto = new Presupuesto(documentoComercial, entidadRealizadora,
                codigoOperacion, detallesPrecio, egreso);
        presupuesto.setFecha(LocalDate.now());
        return presupuesto;
    }


    public Presupuesto buildPresupuestoConDetallesEItems(){
        codigoOperacion = 475;
        documentoComercial = new DocumentoComercial();
        documentoComercial.setNumero(12312312);
        documentoComercial.setTipoDocumento(2);
        entidadRealizadora = new EntidadJuridicaEmpresaBuilder()
                .withNombre("pepe")
                .withVentaAnual(new VentaAnual(2017, 100000F))
                .build();

        Item itemTest = new Item();
        itemTest.setDescripcion("Item de prueba");
        itemTest.setCategoria(new Categoria("Categoria por nombre"));

        DetalleOperacion unDetalleOperacion = new DetalleOperacionBuilder()
                .withCantidad(3)
                .withItem(itemTest)
                .withPrecio(10F)
                .build();
        DetalleOperacion otroDetalleOperacion = new DetalleOperacionBuilder()
                .withCantidad(4)
                .withItem(itemTest)
                .withPrecio(20F)
                .build();

        DetallePrecio unDetallePrecio = new DetallePrecioBuilder()
                .withPrecio(10F)
                .withDetalleOperacion(unDetalleOperacion)
                .build();
        DetallePrecio otroDetallePrecio = new DetallePrecioBuilder()
                .withPrecio(12F)
                .withDetalleOperacion(otroDetalleOperacion)
                .build();

        detallesPrecio.add(unDetallePrecio);
        detallesPrecio.add(otroDetallePrecio);

        egreso = new EgresoBuilder().buildEgresoSimple();

        return build();
    }

}
