package utn.dds.tpAnual.db.dto;

import utn.dds.tpAnual.db.entity.transaccion.Item;

public class ItemDTO extends StandardDTO<Item>{

    //Evaluar si usar categoriaDTO
    private String categoria;

    @Override
    public StandardDTO from(Item object){
        // paso de uno a otro
        return null;
    }
}
