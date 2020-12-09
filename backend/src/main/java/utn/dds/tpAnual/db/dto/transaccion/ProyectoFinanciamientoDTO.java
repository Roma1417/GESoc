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
    private Long id;
    private EntidadDTO entidadRealizadora;
    private UserDTO director;
    private Integer presupuestosMinimos;
    private Float montoMaximoSinPresupuestos;
    private Float montoInicialAsignado;
    private List<EgresoDTO> egresos;
    private List<IngresoDTO> ingresos;

    @Override
    public ProyectoFinanciamientoDTO from(ProyectoFinanciamiento object){
        ProyectoFinanciamientoDTO proyectoDTO = new ProyectoFinanciamientoDTO();
        proyectoDTO.setId(object.getProyectoId());
        proyectoDTO.setMontoInicialAsignado(object.getMontoInicialAsignado());
        if (object.getEntidadRealizadora() != null) {
            proyectoDTO.setEntidadRealizadora(new EntidadDTO().from(object.getEntidadRealizadora()));
        }
        if (object.getDirector() != null) {
            proyectoDTO.setDirector(new UserDTO().from(object.getDirector()));
        }
        proyectoDTO.setPresupuestosMinimos(object.getPresupuestosMinimos());
        proyectoDTO.setMontoMaximoSinPresupuestos(object.getMontoSinPresupuesto());
        if (egresos != null) {
            proyectoDTO.setEgresos(object.getEgresos().stream().map( unEgreso -> new EgresoDTO().from(unEgreso)).collect(Collectors.toList()));
        }
        if (ingresos != null) {
            proyectoDTO.setIngresos(object.getIngresos().stream().map(unIngreso -> new IngresoDTO().from(unIngreso)).collect(Collectors.toList()));
        }
        return proyectoDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Float getMontoInicialAsignado() {
        return montoInicialAsignado;
    }

    public void setMontoInicialAsignado(Float montoInicialAsignado) {
        this.montoInicialAsignado = montoInicialAsignado;
    }

    public List<EgresoDTO> getEgresos() {
        return egresos;
    }

    public void setEgresos(List<EgresoDTO> egresos) {
        this.egresos = egresos;
    }

    public List<IngresoDTO> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<IngresoDTO> ingresos) {
        this.ingresos = ingresos;
    }

    @Override
    public ProyectoFinanciamiento toEntity() {
        return null;
    }


}
