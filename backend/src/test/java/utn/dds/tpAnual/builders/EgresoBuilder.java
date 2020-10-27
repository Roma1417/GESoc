package utn.dds.tpAnual.builders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.proveedor.ProveedorPersona;
import utn.dds.tpAnual.db.entity.transaccion.*;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCompra.CriterioCompra;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCompra.CriterioMenorPrecio;

public class EgresoBuilder {
	
	private int codigoOperacion;
    private List<DetalleOperacion> detallesOperacion = new ArrayList<DetalleOperacion>();
    private int cantidadPresupuestosMinimos;
    private CriterioCompra criterioCompra;
    private List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
    private Set<Usuario> revisores = new HashSet<Usuario>();
    private Entidad entidadRealizadora;
	private LocalDate fechaOperacion;
	private LocalDate fecha;
	private MedioPago medioPago;
	private DocumentoComercial documentoComercial;
	private Proveedor proveedor;

    public EgresoBuilder withCodigoOperacion(int codigoOperacion){
        this.codigoOperacion = codigoOperacion;
        return this;
    }

	public EgresoBuilder withFechaOperacion(LocalDate fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
		return this;
	}

	public EgresoBuilder withFecha(LocalDate fecha) {
		this.fecha = fecha;
		return this;
	}

	public EgresoBuilder withMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
		return this;
	}

	public EgresoBuilder withDocumentoComercial(DocumentoComercial documentoComercial) {
		this.documentoComercial = documentoComercial;
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
	public EgresoBuilder withEntidadRealizadora(Entidad entidadRealizadora){
		this.entidadRealizadora = entidadRealizadora;
		return this;
	}

    
    public Egreso build(){
    	Egreso egreso = new Egreso(documentoComercial, entidadRealizadora, codigoOperacion, detallesOperacion, fechaOperacion,
				null, cantidadPresupuestosMinimos, criterioCompra, presupuestos, proveedor, revisores);
    	egreso.setFecha(fecha);
    	egreso.setProveedor(proveedor);
    	egreso.setMedioPago(medioPago);
        return egreso;
    }
    
    public Egreso buildEgresoSinPresupuestos(){
    	revisores.add(new UsuarioBuilder().withNombre("unRevisor").withContrasenia("asndihg382").withUsuario("unrevisor").build());
    	revisores.add(new UsuarioBuilder().withNombre("otroRevisor").withContrasenia("wuiefnwi471").withUsuario("otrorevisor").build());
    	codigoOperacion = 542;
    	cantidadPresupuestosMinimos = 2;
		documentoComercial = new DocumentoComercial();
		documentoComercial.setNumero(765);
		documentoComercial.setTipoDocumento(1);

		fechaOperacion = LocalDate.now();
		fecha = LocalDate.now();
    	return build();
    }
    public Egreso buildEgresoSimple(){
    	codigoOperacion = 123;
    	cantidadPresupuestosMinimos = 0;
		documentoComercial = new DocumentoComercial();
		documentoComercial.setNumero(765);
		documentoComercial.setTipoDocumento(1);

		fechaOperacion = LocalDate.now();
		fecha = LocalDate.now();
		return build();
	}
    public Egreso buildEgresoCumplidor(){
    	codigoOperacion = 87;
    	cantidadPresupuestosMinimos = 2;
    	criterioCompra = CriterioMenorPrecio.getInstance();
    	revisores.add(new UsuarioBuilder()
    			.withNombre("unRevisor")
    			.withContrasenia("asndihg382")
				.withUsuario("unrevisormas")
    			.build());
    	revisores.add(new UsuarioBuilder()
    			.withNombre("otroRevisor")
    			.withContrasenia("wuiefnwi471")
				.withUsuario("otrorevisormas")
    			.build());
    	
    	DetalleOperacion detalleOperacion = new DetalleOperacionBuilder()
    		.withCantidad(3)
    		.withItem(new Item("itemTest"))
    		.withPrecio(10F)
    		.build();
    	
    	detallesOperacion.add(detalleOperacion);
    	detallesOperacion.add(detalleOperacion);
    	
    	DetallePrecio unDetallePrecio = new DetallePrecioBuilder()
				.withPrecio(10F)
				.withDetalleOperacion(detalleOperacion)
				.build();
    	
    	Presupuesto unPresupuesto = new PresupuestoBuilder()
    			.withCodigoOperacion(45532)
    			.withDetallePrecio(unDetallePrecio)
    			.withDetallePrecio(unDetallePrecio)
    			.build();
    	
    	presupuestos.add(unPresupuesto);
    	presupuestos.add(unPresupuesto);
    	return build();
    }
    
    public Egreso buildEgresoConDetallesDeDistintoTamanio(){
    	codigoOperacion = 47587;
    	cantidadPresupuestosMinimos = 2;
    	Item itemTest = new Item("item Test Test");
    	
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
    			.withCodigoOperacion(178662)
    			.withDetallePrecio(unDetallePrecio)
    			.withDetallePrecio(unDetallePrecio)
    			.build();
    	Presupuesto otroPresupuesto = new PresupuestoBuilder()
    			.withCodigoOperacion(176623)
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
    	codigoOperacion = 34555;
    	cantidadPresupuestosMinimos = 25;
    	Item itemTest = new Item("itemTestTest");
    	
    	DetalleOperacion unDetalleOperacion = new DetalleOperacionBuilder()
    		.withCantidad(53)
    		.withItem(itemTest)
    		.withPrecio(510F)
    		.build();
    	DetalleOperacion otroDetalleOperacion = new DetalleOperacionBuilder()
        		.withCantidad(45)
        		.withItem(itemTest)
        		.withPrecio(250F)
        		.build();
    	
    	DetallePrecio unDetallePrecio = new DetallePrecioBuilder()
				.withPrecio(105F)
				.withDetalleOperacion(unDetalleOperacion)
				.build();
    	DetallePrecio otroDetallePrecio = new DetallePrecioBuilder()
				.withPrecio(152F)
				.withDetalleOperacion(otroDetalleOperacion)
				.build();

    	Presupuesto otroPresupuesto = new PresupuestoBuilder()
    			.withCodigoOperacion(1752553)
    			.withDetallePrecio(unDetallePrecio)
    			.withDetallePrecio(otroDetallePrecio)
    			.build();
    	detallesOperacion.add(unDetalleOperacion);
    	detallesOperacion.add(otroDetalleOperacion);
    	
    	presupuestos.add(otroPresupuesto);
    	presupuestos.add(otroPresupuesto);
    	return build();
    }

	public Egreso buildUnEgresoCompleto(){
		codigoOperacion = 111;
		cantidadPresupuestosMinimos = 3;
		documentoComercial = new DocumentoComercial();
		documentoComercial.setNumero(6784);
		documentoComercial.setTipoDocumento(5);

		fechaOperacion = LocalDate.now();
		fecha = LocalDate.now();

		Item itemTest = new Item();
		itemTest.setDescripcion("Item de prueba 2");

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
				.withCodigoOperacion(1723)
				.withDetallePrecio(unDetallePrecio)
				.withDetallePrecio(otroDetallePrecio)
				.build();

		detallesOperacion.add(unDetalleOperacion);
		detallesOperacion.add(otroDetalleOperacion);

		presupuestos.add(unPresupuesto);
		return build();
	}

	public Egreso buildEgresoCompletoConFecha(LocalDate localDate){
		codigoOperacion = 22223;
		cantidadPresupuestosMinimos = 1;
		documentoComercial = new DocumentoComercial();
		documentoComercial.setNumero(3473);
		documentoComercial.setTipoDocumento(1);
		proveedor = new ProveedorPersona(null, "9876543", "San pepito");
		medioPago = new MedioPago("Tarjeta de crédito");

		fechaOperacion = localDate;
		fecha = localDate;

		Item itemTest = new Item();
		itemTest.setDescripcion("Item de prueba 3");

		DetalleOperacion unDetalleOperacion = new DetalleOperacionBuilder()
				.withCantidad(2)
				.withItem(itemTest)
				.withPrecio(100F)
				.build();

		DetalleOperacion otroDetalleOperacion = new DetalleOperacionBuilder()
				.withCantidad(6)
				.withItem(itemTest)
				.withPrecio(260F)
				.build();

		DetallePrecio unDetallePrecio = new DetallePrecioBuilder()
				.withPrecio(103F)
				.withDetalleOperacion(unDetalleOperacion)
				.build();

		DetallePrecio otroDetallePrecio = new DetallePrecioBuilder()
				.withPrecio(123F)
				.withDetalleOperacion(otroDetalleOperacion)
				.build();

		Presupuesto unPresupuesto = new PresupuestoBuilder()
				.withCodigoOperacion(7654)
				.withDetallePrecio(unDetallePrecio)
				.withDetallePrecio(otroDetallePrecio)
				.build();

		detallesOperacion.add(unDetalleOperacion);
		detallesOperacion.add(otroDetalleOperacion);

		presupuestos.add(unPresupuesto);
		return build();
	}

	public Egreso buildEgresoCompletoConFechaSinPresupuestos(LocalDate localDate){
		codigoOperacion = 28993;
		cantidadPresupuestosMinimos = 1;
		documentoComercial = new DocumentoComercial();
		documentoComercial.setNumero(3473);
		documentoComercial.setTipoDocumento(1);
		documentoComercial.setPais(new Pais("Argentina", "1234Arg"));
		documentoComercial.setMoneda(new Moneda("123Pesos", "Pesos", "$"));

		proveedor = new ProveedorPersona(null, "9876543", "San pepito");
		medioPago = new MedioPago("Tarjeta de crédito");

		fechaOperacion = localDate;
		fecha = localDate;

		Item itemTest = new Item();
		itemTest.setDescripcion("Item de prueba 3");

		DetalleOperacion unDetalleOperacion = new DetalleOperacionBuilder()
				.withCantidad(2)
				.withItem(itemTest)
				.withPrecio(100F)
				.build();

		DetalleOperacion otroDetalleOperacion = new DetalleOperacionBuilder()
				.withCantidad(6)
				.withItem(itemTest)
				.withPrecio(260F)
				.build();

		detallesOperacion.add(unDetalleOperacion);
		detallesOperacion.add(otroDetalleOperacion);

		return build();
	}

	public Egreso buildOtroEgresoCompleto(){
		return buildEgresoCompletoConFecha(LocalDate.now());
	}

	public EgresoBuilder withProveedor(Proveedor proveedor) {
    	this.proveedor = proveedor;
    	return this;
	}
}
