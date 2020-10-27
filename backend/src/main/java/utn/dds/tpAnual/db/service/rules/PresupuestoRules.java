package utn.dds.tpAnual.db.service.rules;

import utn.dds.tpAnual.db.dto.transaccion.EgresoDTO;
import utn.dds.tpAnual.db.dto.transaccion.PresupuestoDTO;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.transaccion.*;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PresupuestoRules {
    private static final PresupuestoRules instance = new PresupuestoRules();

    private PresupuestoRules(){

    }

    public static PresupuestoRules getInstance(){
        return instance;
    }

    public void validarCrearPresupuesto(Optional<Egreso> egreso, PresupuestoDTO presupuestoDTO, List<DetallePrecio> detallesPrecio,
                                        Optional<Entidad> entidadRealizadora, Optional<Pais> pais, Optional<Moneda> moneda,
                                        DocumentoComercial documentoComercial){
        if (!egreso.isPresent()) {
            throw new RuntimeException("El egreso al que se quiere asociar al presupuesto no existe " +
                    "o no está asociado al usuario");
        }
        if (!detallesOperacionPertenecenAlEgreso(egreso, detallesPrecio.stream()
                .map(detallePrecio -> detallePrecio.getDetalleOperacion()).collect(Collectors.toList()))) {
            throw new RuntimeException("Los detalles de operación de los detalles de precio no coinciden " +
                    "con los detalles de operación del egreso");
        }
        if (!entidadRealizadora.isPresent()) {
            throw new RuntimeException("La entidad del presupuesto no existe");
        }
        if (documentoComercial.getPais() == null){
            throw new RuntimeException("Pais no existe");
        }
        if (documentoComercial.getMoneda() == null){
            throw new RuntimeException("Moneda no existe");
        }
        if (detallesPrecio == null || detallesPrecio.size() == 0){
            throw new RuntimeException("No hay detalles de precios cargados");
        }
        if (presupuestoDTO.getDocumentoComercial() == null){
            throw new RuntimeException("El presupuesto no posee documento comercial");
        }
    }

    private boolean detallesOperacionPertenecenAlEgreso(Optional<Egreso> egreso, List<DetalleOperacion> detallesOperacion) {
        List<Long> idsOperaciones = egreso.get().getDetallesOperacion()
                .stream().map(detalleOperacion -> detalleOperacion.getDetalleOperacionId()).collect(Collectors.toList());
        return detallesOperacion
                .stream().allMatch(detalleOperacion -> idsOperaciones.contains(detalleOperacion.getDetalleOperacionId()));
    }
}
