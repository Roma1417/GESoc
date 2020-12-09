package utn.dds.tpAnual.db.entity.transaccion;

import utn.dds.tpAnual.db.entity.EntityInterface;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.usuario.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PROYECTO_FINANCIAMIENTO")
public class ProyectoFinanciamiento implements EntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long proyectoId;

    @Column(name = "MONTO_SIN_PRESUPUESTO")
    private Float montoSinPresupuesto;

    @Column(name = "MONTO_INICIAL_ASIGNADO")
    private Float montoInicialAsignado;

    @Column(name = "PRESUPUESTOS_MINIMOS")
    private Integer presupuestosMinimos;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Egreso> egresos;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ingreso> ingresos;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Usuario director;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Entidad entidadRealizadora;

    public ProyectoFinanciamiento(Float montoSinPresupuesto, Integer presupuestosMinimos,
                                  Set<Egreso> egresos, Set<Ingreso> ingresos, Usuario director,
                                  Entidad entidadRealizadora) {
        this.montoSinPresupuesto = montoSinPresupuesto;
        this.presupuestosMinimos = presupuestosMinimos;
        this.egresos = egresos;
        this.ingresos = ingresos;
        this.director = director;
        this.entidadRealizadora = entidadRealizadora;
    }

    public ProyectoFinanciamiento(){}

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

    public Set<Egreso> getEgresos() {
        return egresos;
    }

    public void setEgresos(Set<Egreso> egresos) {
        this.egresos = egresos;
    }

    public Set<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(Set<Ingreso> ingresos) {
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

    public Float getMontoInicialAsignado() {
        return montoInicialAsignado;
    }

    public void setMontoInicialAsignado(Float montoInicialAsignado) {
        this.montoInicialAsignado = montoInicialAsignado;
    }

    public void vincularEgreso(Egreso egreso) {
        if (egresos == null) {
            egresos = new HashSet<>();
        }
        egresos.add(egreso);
        egreso.setProyecto(this);
    }

    public void vincularIngreso(Ingreso ingreso) {
        if (ingresos == null) {
            ingresos= new HashSet<>();
        }
        ingresos.add(ingreso);
        ingreso.setProyecto(this);
    }

    @Override
    public Long getId() {
        return proyectoId   ;
    }
}
