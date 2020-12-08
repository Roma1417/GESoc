package utn.dds.tpAnual.db.entity.transaccion;

import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.usuario.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROYECTO_FINANCIAMIENTO")
public class ProyectoFinanciamiento{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long proyectoId;

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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Entidad entidadRealizadora;

    public Long getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Long proyectoId) {
        this.proyectoId = proyectoId;
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

    public Entidad getEntidadRealizadora() {
        return entidadRealizadora;
    }

    public void setEntidadRealizadora(Entidad entidadRealizadora) {
        this.entidadRealizadora = entidadRealizadora;
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
