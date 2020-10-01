package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.ItemDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.service.jpaService.ItemService;

@Service
public class ItemResourceBean {

    @Autowired
    private ItemService itemService;

    public ItemDTO vincularCategoria(ItemDTO itemDTO){
        return null;
    }

    public PageableResponse<ItemDTO, Ingreso> getItems(PageableRequest pageableRequest) {
        return null;
    }
}
