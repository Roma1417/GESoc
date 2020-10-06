package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.repository.ActividadRepository;
import utn.dds.tpAnual.db.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService extends CustomJPAService<Item> {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public JpaRepository<Item, Long> getRepository() {
        return itemRepository;
    }

    public Page<Item> getItemsByDescripcionLike (String descripcion, PageableRequest pageableRequest){
        Pageable pageable = PageRequest.of(pageableRequest.getPage().intValue(), pageableRequest.getItemsPerPage().intValue());
        return itemRepository.getItemsByDescripcionLike(descripcion,pageable);
    }

}
