package utn.dds.tpAnual.builders;

import java.util.ArrayList;
import java.util.List;

import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.DetallePrecio;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.entity.transaccion.Presupuesto;
import utn.dds.tpAnual.usuario.Usuario;
import utn.dds.tpAnual.validador.CriterioCompra;
import utn.dds.tpAnual.validador.CriterioMenorPrecio;

public class EgresoBuilder {
	
	private int codigoOperacion;
    private List<DetalleOperacion> detallesOperacion = new ArrayList<DetalleOperacion>();
    private int cantidadPresupuestosMinimos;
    private CriterioCompra criterioCompra;
    private List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
    private List<Usuario> revisores = new ArrayList<Usuario>();

    public EgresoBuilder withCodigoOperacion(int codigoOperacion){
        this.codigoOperacion = codigoOperacion;
        return this;
    }
    public EgresoBuilder withDetalleOperacion(DetalleOperacion detalleOperacion){
        this.detallesOperacion.add(detalleOperacion);
        return this;
    }
    public EgresoBuilder withCantidadPresupuestosMinimos(int cantidadPresupuestosMinimos){
        this.cantidadPresupuestosMinimos = cantidadPresupuestosMinimos;
        return this;
    }
    public EgresoBuilder withCriterioCompra(CriterioCompra criterioCompra){
        this.criterioCompra = criterioCompra;
        return this;
    }
    public EgresoBuilder withPresupuesto(Presupuesto presupuesto){
        this.presupuestos.add(presupuesto);
        return this;
    }
    public EgresoBuilder withRevisor(Usuario revisor){
        this.revisores.add(revisor);
        return this;
    }
    
    public Egreso build(){
        return new Egreso(null, null, codigoOperacion, detallesOperacion, null, null, cantidadPresupuestosMinimos, criterioCompra, presupuestos, null, revisores);
    }
    
    public Egreso buildEgresoSinPresupuestos(){
    	revisores.add(new UsuarioBuilder().withNombre("unRevisor").withContrasenia("asndihg382").build());
    	revisores.add(new UsuarioBuilder().withNombre("otroRevisor").withContrasenia("wuiefnwi471").build());
    	criterioCompra = CriterioMenorPrecio.getInstance();
    	codigoOperacion = 542;
    	cantidadPresupuestosMinimos = 2;
    	return build();
    }
    public Egreso buildEgresoSimple(){
    	codigoOperacion = 123;
    	cantidadPresupuestosMinimos = 0;
    	return build();
    }
    public Egreso buildEgresoCumplidor(){
    	codigoOperacion = 87;
    	cantidadPresupuestosMinimos = 2;
    	criterioCompra = CriterioMenorPrecio.getInstance();
    	revisores.add(new UsuarioBuilder()
    			.withNombre("unRevisor")
    			.withContrasenia("asndihg382")
    			.build());
    	revisores.add(new UsuarioBuilder()
    			.withNombre("otroRevisor")
    			.withContrasenia("wuiefnwi471")
    			.build());
    	
    	DetalleOperacion detalleOperacion = new DetalleOperacionBuilder()
    		.withCantidad(3)
    		.withItem(new Item(123L, "itemTest"))
    		.withPrecio(10F)
    		.build();
    	
    	detallesOperacion.add(detalleOperacion);
    	detallesOperacion.add(detalleOperacion);
    	
    	DetallePrecio unDetallePrecio = new DetallePrecioBuilder()
				.withPrecio(10F)
				.withDetalleOperacion(detalleOperacion)
				.build();
    	
    	Presupuesto unPresupuesto = new PresupuestoBuilder()
    			.withCodigoOperacion(1782)
    			.withDetallePrecio(unDetallePrecio)
    			.withDetallePrecio(unDetallePrecio)
    			.build();
    	
    	presupuestos.add(unPresupuesto);
    	presupuestos.add(unPresupuesto);
    	return build();
    }
    
    public Egreso buildEgresoConDetallesDeDistintoTamanio(){
    	codigoOperacion = 475;
    	cantidadPresupuestosMinimos = 2;
    	Item itemTest = new Item(123L, "itemTest");
    	
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

    	Presupuesto unPresupuesto = new PresupuestoBuilder()
    			.withCodigoOperacion(1782)
    			.withDetallePrecio(unDetallePrecio)
    			.withDetallePrecio(unDetallePrecio)
    			.build();
    	Presupuesto otroPresupuesto = new PresupuestoBuilder()
    			.withCodigoOperacion(1723)
    			.withDetallePrecio(unDetallePrecio)
    			.withDetallePrecio(otroDetallePrecio)
    			.build();
    	
    	detallesOperacion.add(unDetalleOperacion);
    	detallesOperacion.add(unDetalleOperacion);
    	
    	presupuestos.add(unPresupuesto);
    	presupuestos.add(unPresupuesto);
    	presupuestos.add(otroPresupuesto);
    	presupuestos.add(otroPresupuesto);
    	return build();
    }
    
    public Egreso buildEgresoNoBasadoEnPresupuesto(){
    	codigoOperacion = 345;
    	cantidadPresupuestosMinimos = 2;
    	Item itemTest = new Item(123L, "itemTest");
    	
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

    	Presupuesto otroPresupuesto = new PresupuestoBuilder()
    			.withCodigoOperacion(1723)
    			.withDetallePrecio(unDetallePrecio)
    			.withDetallePrecio(otroDetallePrecio)
    			.build();
    	detallesOperacion.add(unDetalleOperacion);
    	detallesOperacion.add(otroDetalleOperacion);
    	
    	presupuestos.add(otroPresupuesto);
    	presupuestos.add(otroPresupuesto);
    	return build();
    }
    
}
