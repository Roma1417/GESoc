package utn.dds.tpAnual.afip.builders;

import java.util.List;

import utn.dds.tpAnual.transaccion.DetalleOperacion;
import utn.dds.tpAnual.transaccion.Egreso;
import utn.dds.tpAnual.transaccion.Presupuesto;
import utn.dds.tpAnual.usuario.Usuario;
import utn.dds.tpAnual.validador.CriterioCompra;

public class EgresoBuilder {
	
	// int codigoOperacion, List<DetalleOperacion> detallesOperacion, LocalDate fechaOperacion, MedioPago medioPago, int cantidadPresupuestosMinimos, CriterioCompra criterioCompra, List<Presupuesto> presupuestos, Proveedor proveedor, List<Usuario> revisores)
	private Egreso egresoSinPresupuestos = new Egreso(null, null, 542, null, null, null, 2, criterioMenorPrecio, listaPresupuestosVacia, null, revisoresTest);
	private Egreso egresoCumplidor = new Egreso(null, null, 87, unosDetallesOperacion, null, null, 2, criterioMenorPrecio, unaListaPresupuestos, null, revisoresTest);

	private int codigoOperacion;
    private List<DetalleOperacion> detallesOperacion;
    private int cantidadPresupuestosMinimos;
    private CriterioCompra criterioCompra;
    private List<Presupuesto> presupuestos;
    private List<Usuario> revisores;

    public EgresoBuilder withCodigoOperacion(int codigoOperacion){
        this.codigoOperacion = codigoOperacion;
        return this;
    }
    
    public EgresoBuilder withDetallesOperacion(List<DetalleOperacion> detallesOperacion){
        this.detallesOperacion = detallesOperacion;
        return this;
    }
    
    public EgresoBuilder withCantidadPresupuestosMinimos(int cantidadPresupuestosMinimos){
        this.cantidadPresupuestosMinimos = cantidadPresupuestosMinimos;
        return this;
    }

    
    public EgresoBuilder withdetallesOperacion(List<DetalleOperacion> detallesOperacion){
        this.detallesOperacion = detallesOperacion;
        return this;
    }

    
    public EgresoBuilder withdetallesOperacion(List<DetalleOperacion> detallesOperacion){
        this.detallesOperacion = detallesOperacion;
        return this;
    }


    public Egreso build(){
        return new Egreso(null, null, codigoOperacion, detallesOperacion, null, null, cantidadPresupuestosMinimos, criterioCompra, presupuestos, null, revisores);
    }
}

