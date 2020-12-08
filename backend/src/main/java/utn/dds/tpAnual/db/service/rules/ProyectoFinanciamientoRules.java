package utn.dds.tpAnual.db.service.rules;

import utn.dds.tpAnual.db.dto.transaccion.PresupuestoDTO;
import utn.dds.tpAnual.db.dto.transaccion.ProyectoFinanciamientoDTO;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.*;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.entity.usuario.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProyectoFinanciamientoRules {
    private static final ProyectoFinanciamientoRules instance = new ProyectoFinanciamientoRules();

    private ProyectoFinanciamientoRules(){

    }

    public static ProyectoFinanciamientoRules getInstance(){
        return instance;
    }

    public void validarCrearProyectoFinanciamiento(ProyectoFinanciamientoDTO proyectoDTO, Optional<Entidad> entidadRealizadora, Usuario usuario){
        if (!entidadRealizadora.isPresent()) {
            throw new RuntimeException("La entidad del proyecto de financiación no existe.");
        }
        if (usuario == null) {
            throw new RuntimeException("El director asignado no existe.");
        }
        if (proyectoDTO.getPresupuestosMinimos() <= 0) {
            throw new RuntimeException("La cantidad de presupuestos minimos debe ser mayor a 0.");
        }
        if (proyectoDTO.getMontoMaximoSinPresupuestos() <= 0) {
            throw new RuntimeException("El monto maximo sin presupuestos debe ser mayor a 0.");
        }
    }
    public void validarVinculacion(ProyectoFinanciamiento proyecto, Egreso egreso){
        Entidad entidad = egreso.getEntidadRealizadora();
        if(entidad.getEntidadId() != proyecto.getEntidadRealizadora().getEntidadId()) {
            throw new RuntimeException("El proyecto y el egreso pertenecen a distintas entidades");
        }
        if(egreso.getProyecto() != null){
            throw new RuntimeException("El egreso ya se encuentra vinculado a un proyecto");
        }
        if(egreso.getPresupuestos().size() < proyecto.getPresupuestosMinimos()
                && egreso.getTotal() > proyecto.getMontoSinPresupuesto() ){
            throw new RuntimeException("El egreso no cubre los requisitos de presupuestos estipulados por el proyecto");
        }

    }

    public void validarVinculacion(ProyectoFinanciamiento proyecto, Ingreso ingreso){
        Entidad entidad = ingreso.getEntidadRealizadora();
        if(entidad.getEntidadId() != proyecto.getEntidadRealizadora().getEntidadId()) {
            throw new RuntimeException("El proyecto y el ingreso pertenecen a distintas entidades");
        }
        if(ingreso.getProyecto() != null){
            throw new RuntimeException("El ingreso ya se encuentra vinculado a un proyecto");
        }
    }


}
