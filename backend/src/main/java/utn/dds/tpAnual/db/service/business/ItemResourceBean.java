package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.transaccion.ItemDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.service.jpaService.CategoriaService;
import utn.dds.tpAnual.db.service.jpaService.ItemService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemResourceBean {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoriaService categoriaService;

    public ItemDTO vincularCategoria(ItemDTO itemDTO){
        Optional<Item> item = itemService.findById(itemDTO.getId());
        if (!item.isPresent()) {
            throw new ValidationException("Item no encontrado");
        }
        List<Long> categoriaIds = itemDTO.getCategorias()
                .stream()
                .map(categoriaDTO -> categoriaDTO.getId())
                .collect(Collectors.toList());
        List<Categoria> categorias = categoriaService.findAllById(categoriaIds);
        if (categorias.isEmpty()) {
            throw new ValidationException("Categoria no encontrada");
        }
        item.get().setCategorias(categorias);
        itemService.save(item.get());
        return new ItemDTO().from(item.get());
    }

    public PageableResponse<ItemDTO, Item> getItems(PageableRequest pageableRequest, String itemName) {
        Page<Item> items = itemService.getItemsByDescripcionLike(itemName, pageableRequest);
        return new PageableResponse().fromPage(items, new ItemDTO());
    }


}
