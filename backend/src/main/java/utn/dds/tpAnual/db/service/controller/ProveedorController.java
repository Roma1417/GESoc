package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.proveedor.ProveedorDTO;
import utn.dds.tpAnual.db.dto.transaccion.ItemDTO;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.service.business.ItemResourceBean;
import utn.dds.tpAnual.db.service.business.ProveedorResourceBean;

@RestController
@CrossOrigin(origins="https://gesoc-app.herokuapp.com", allowCredentials = "true")
@RequestMapping("/api/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorResourceBean proveedorResourceBean;

    @RequestMapping("")
    public PageableResponse<ProveedorDTO, Proveedor> getProveedores(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                                    @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage,
                                                                    @RequestParam(name ="name", required = false) String proveedorName){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<ProveedorDTO, Proveedor> proveedores = proveedorResourceBean.getProveedores(pageableRequest, proveedorName);
        return proveedores;
    }
}
