package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.proveedor.ProveedorDTO;
import utn.dds.tpAnual.db.dto.ubicacion.PaisDTO;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.service.jpaService.PaisService;
import utn.dds.tpAnual.db.service.jpaService.ProveedorService;

@Service
public class PaisResourceBean {

    @Autowired
    private PaisService paisService;

    public PageableResponse<PaisDTO, Pais> getPaises(PageableRequest pageableRequest, String paisName) {
        Page<Pais> paises = paisService.getPaises(pageableRequest, paisName);
        return new PageableResponse().fromPage(paises, new PaisDTO());
    }
}
