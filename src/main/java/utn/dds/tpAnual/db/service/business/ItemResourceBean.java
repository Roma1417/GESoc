package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.ItemDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.service.jpaService.CategoriaService;
import utn.dds.tpAnual.db.service.jpaService.ItemService;

import java.util.Optional;

@Service
public class ItemResourceBean {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoriaService categoriaService;

    public ItemDTO vincularCategoria(ItemDTO itemDTO){
        Categoria categoria = categoriaService.getCategoriaByDescripcion(itemDTO.getCategoria().getDescripcion());
        Optional<Item> item = itemService.findById(itemDTO.getId());
        if (categoria == null || !item.isPresent()) {
            throw new ValidationException("Datos inválidos");
        }
        item.get().setCategoria(categoria);
        itemService.save(item.get());
        return new ItemDTO().from(item.get());
    }

    public PageableResponse<ItemDTO, Item> getItems(PageableRequest pageableRequest, String itemName) {
        Page<Item> items = itemService.getItemsByDescripcionLike(itemName, pageableRequest);
        return new PageableResponse().fromPage(items, new ItemDTO());
    }


}
