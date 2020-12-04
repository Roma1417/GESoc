package utn.dds.tpAnual.db.entity.transaccion;

import utn.dds.tpAnual.db.entity.usuario.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROYECTO_FINANCIAMIENTO")
public class ProyectoFinanciamiento extends Operacion{

    @Column(name = "MONTO_SIN_PRESUPUESTO")
    private Float montoSinPresupuesto;

    @Column(name = "PRESUPUESTOS_MINIMOS")
    private Integer presupuestosMinimos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Egreso> egresos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ingreso> ingresos;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Usuario director;

    public Float getTotal() {
        return 0F;
    }

    public Float getMontoSinPresupuesto() {
        return montoSinPresupuesto;
    }

    public void setMontoSinPresupuesto(Float montoSinPresupuesto) {
        this.montoSinPresupuesto = montoSinPresupuesto;
    }

    public Integer getPresupuestosMinimos() {
        return presupuestosMinimos;
    }

    public void setPresupuestosMinimos(Integer presupuestosMinimos) {
        this.presupuestosMinimos = presupuestosMinimos;
    }

    public List<Egreso> getEgresos() {
        return egresos;
    }

    public void setEgresos(List<Egreso> egresos) {
        this.egresos = egresos;
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

    public Usuario getDirector() {
        return director;
    }

    public void setDirector(Usuario director) {
        this.director = director;
    }

    public void vincularEgreso(Egreso egreso) {
        if (egresos == null) {
            egresos= new ArrayList<>();
        }
        egresos.add(egreso);
        egreso.setProyecto(this);
    }

    public void vincularIngreso(Ingreso ingreso) {
        if (ingresos == null) {
            ingresos= new ArrayList<>();
        }
        ingresos.add(ingreso);
        ingreso.setProyecto(this);
    }
}
