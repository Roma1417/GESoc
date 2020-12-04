package utn.dds.tpAnual.db.dto.transaccion;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.dto.entidad.EntidadDTO;
import utn.dds.tpAnual.db.dto.proveedor.ProveedorDTO;
import utn.dds.tpAnual.db.dto.usuario.UserDTO;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.transaccion.ProyectoFinanciamiento;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ProyectoFinanciamientoDTO extends StandardDTO<ProyectoFinanciamiento> {
    private Long idProyecto;
    private EntidadDTO entidadRealizadora;
    private UserDTO director;
    private Integer presupuestosMinimos;
    private Float montoMaximoSinPresupuestos;
    private List<Egreso> egresos;
    private List<Ingreso> ingresos;
    private Float total;

    @Override
    public ProyectoFinanciamientoDTO from(ProyectoFinanciamiento object){
        ProyectoFinanciamientoDTO proyectoDTO = new ProyectoFinanciamientoDTO();
        proyectoDTO.setIdProyecto(object.getOperacionId());
        if (object.getEntidadRealizadora() != null) {
            proyectoDTO.setEntidadRealizadora(new EntidadDTO().from(object.getEntidadRealizadora()));
        }
        if (object.getDirector() != null) {
            proyectoDTO.setDirector(new UserDTO().from(object.getDirector()));
        }
        proyectoDTO.setTotal(object.getTotal());
        proyectoDTO.setPresupuestosMinimos(object.getPresupuestosMinimos());
        proyectoDTO.setMontoMaximoSinPresupuestos(object.getMontoSinPresupuesto());
        proyectoDTO.setEgresos(object.getEgresos());
        proyectoDTO.setIngresos(object.getIngresos());
        return proyectoDTO;
    }

    @Override
    public ProyectoFinanciamiento toEntity() {
        return null;
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public EntidadDTO getEntidadRealizadora() {
        return entidadRealizadora;
    }

    public void setEntidadRealizadora(EntidadDTO entidadRealizadora) {
        this.entidadRealizadora = entidadRealizadora;
    }

    public UserDTO getDirector() {
        return director;
    }

    public void setDirector(UserDTO director) {
        this.director = director;
    }

    public Integer getPresupuestosMinimos() {
        return presupuestosMinimos;
    }

    public void setPresupuestosMinimos(Integer presupuestosMinimos) {
        this.presupuestosMinimos = presupuestosMinimos;
    }

    public Float getMontoMaximoSinPresupuestos() {
        return montoMaximoSinPresupuestos;
    }

    public void setMontoMaximoSinPresupuestos(Float montoMaximoSinPresupuestos) {
        this.montoMaximoSinPresupuestos = montoMaximoSinPresupuestos;
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

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
