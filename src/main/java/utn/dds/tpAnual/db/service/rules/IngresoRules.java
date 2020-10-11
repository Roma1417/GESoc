package utn.dds.tpAnual.db.service.rules;

import utn.dds.tpAnual.db.dto.transaccion.IngresoDTO;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.DocumentoComercial;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;

import java.util.Optional;

public class IngresoRules {
    private static final IngresoRules instance = new IngresoRules();

    private IngresoRules(){

    }

    public static IngresoRules getInstance(){
        return instance;
    }

    public void validarCrearIngreso(Optional<Entidad> entidadRealizadora, DocumentoComercial documentoComercial,
                                    Ingreso ingreso, Optional<Pais> pais, Optional<Moneda> moneda){

        if (!entidadRealizadora.isPresent()) {
            throw new RuntimeException("Entidad no existe");
        }
        if (!pais.isPresent()){
            throw new RuntimeException("Pais no existe");
        }
        if (!moneda.isPresent()){
            throw new RuntimeException("Moneda no existe");
        }
        if(documentoComercial.getNumero() == null){
            throw new RuntimeException("Número de documento comercial incompleto");
        }
        if(documentoComercial.getTipoDocumento() == null){
            throw new RuntimeException("Tipo de documento comercial incompleto");
        }
        if(ingreso.getTotal() <= 0F){
            throw new RuntimeException("El total debe ser positivo");
        }
        if(ingreso.getCodigoOperacion() == null){
            throw new RuntimeException("Codigo de operacion no ingresado");
        }

    }
}
