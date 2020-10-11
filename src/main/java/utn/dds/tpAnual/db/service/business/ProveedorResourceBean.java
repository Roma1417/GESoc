package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.proveedor.ProveedorDTO;
import utn.dds.tpAnual.db.dto.usuario.MensajeDTO;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.service.jpaService.MensajeService;
import utn.dds.tpAnual.db.service.jpaService.ProveedorService;

@Service
public class ProveedorResourceBean {

    @Autowired
    private ProveedorService proveedorService;

    public PageableResponse<ProveedorDTO, Proveedor> getProveedores(PageableRequest pageableRequest, String proveedorName) {
        Page<Proveedor> proveedores = proveedorService.getProveedores(pageableRequest, proveedorName);
        return new PageableResponse().fromPage(proveedores, new ProveedorDTO());
    }
}
