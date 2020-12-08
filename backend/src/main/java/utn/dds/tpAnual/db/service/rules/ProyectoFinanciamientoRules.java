package utn.dds.tpAnual.db.service.rules;

import utn.dds.tpAnual.db.dto.transaccion.PresupuestoDTO;
import utn.dds.tpAnual.db.dto.transaccion.ProyectoFinanciamientoDTO;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.DetalleOperacion;
import utn.dds.tpAnual.db.entity.transaccion.DetallePrecio;
import utn.dds.tpAnual.db.entity.transaccion.DocumentoComercial;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
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
    }
}
