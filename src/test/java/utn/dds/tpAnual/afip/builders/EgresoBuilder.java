package utn.dds.tpAnual.afip.builders;

import java.util.List;

import utn.dds.tpAnual.transaccion.DetalleOperacion;
import utn.dds.tpAnual.transaccion.Egreso;
import utn.dds.tpAnual.transaccion.Presupuesto;
import utn.dds.tpAnual.usuario.Usuario;
import utn.dds.tpAnual.validador.CriterioCompra;

public class EgresoBuilder {
	
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
    public EgresoBuilder withCriterioCompra(CriterioCompra criterioCompra){
        this.criterioCompra = criterioCompra;
        return this;
    }
    public EgresoBuilder withPresupuestos(List<Presupuesto> presupuestos){
        this.presupuestos = presupuestos;
        return this;
    }
    public EgresoBuilder withRevisores(List<Usuario> revisores){
        this.revisores = revisores;
        return this;
    }
    public Egreso build(){
        return new Egreso(null, null, codigoOperacion, detallesOperacion, null, null, cantidadPresupuestosMinimos, criterioCompra, presupuestos, null, revisores);
    }
}

