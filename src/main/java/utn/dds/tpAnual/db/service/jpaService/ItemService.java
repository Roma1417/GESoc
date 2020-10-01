package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.afip.Actividad;
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

}
