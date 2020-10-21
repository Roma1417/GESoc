package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import utn.dds.tpAnual.db.dto.transaccion.ItemDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.service.business.ItemResourceBean;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemResourceBean itemResourceBean;

    @RequestMapping("item")
    public PageableResponse<ItemDTO, Item> getItems(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                       @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage,
                                                       @RequestParam(name ="name", required = false) String itemName){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<ItemDTO, Item> items = itemResourceBean.getItems(pageableRequest, itemName);
        return items;
    }

    @PatchMapping("categoria")
    public ItemDTO vincularItemCategoria(@RequestBody ItemDTO itemDTO){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ItemDTO itemVinculado = itemResourceBean.vincularCategoria(itemDTO);
        return itemVinculado;
    }
}
