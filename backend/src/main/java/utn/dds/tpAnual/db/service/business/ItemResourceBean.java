package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.categoria.CategoriaDTO;
import utn.dds.tpAnual.db.dto.complex.VinculacionItemCategoriaDTO;
import utn.dds.tpAnual.db.dto.transaccion.ItemDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.service.jpaService.CategoriaService;
import utn.dds.tpAnual.db.service.jpaService.ItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemResourceBean {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoriaService categoriaService;

    public ItemDTO vincularCategoria(VinculacionItemCategoriaDTO itemCategoriaDTO){
        Optional<Item> item = itemService.findById(itemCategoriaDTO.getItemId());
        if (!item.isPresent()) {
            throw new ValidationException("Item no encontrado");
        }
        Optional<Categoria> categoria = categoriaService.findById(itemCategoriaDTO.getCategoriaId());
        if (!categoria.isPresent()) {
            throw new ValidationException("Categoria no encontrada");
        }
        item.get().addCategoria(categoria.get());
        itemService.save(item.get());
        return new ItemDTO().from(item.get());
    }

    public PageableResponse<ItemDTO, Item> getItems(PageableRequest pageableRequest, String itemName) {
        Page<Item> items = itemService.getItemsByDescripcionLike(itemName, pageableRequest);
        return new PageableResponse().fromPage(items, new ItemDTO());
    }


}
