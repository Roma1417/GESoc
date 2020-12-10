package utn.dds.tpAnual.db.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.transaccion.ItemDTO;
import utn.dds.tpAnual.db.dto.transaccion.MedioPagoDTO;
import utn.dds.tpAnual.db.entity.transaccion.Item;
import utn.dds.tpAnual.db.entity.transaccion.MedioPago;
import utn.dds.tpAnual.db.service.business.ItemResourceBean;
import utn.dds.tpAnual.db.service.business.MedioPagoResourceBean;

@RestController
@CrossOrigin(origins="https://gesoc-app.herokuapp.com", allowCredentials = "true")
@RequestMapping("/api/MedioPago")
public class MedioPagoController {

    @Autowired
    private MedioPagoResourceBean medioPagoResourceBean;

    @RequestMapping("")
    public PageableResponse<MedioPagoDTO, MedioPago> getMediosDePago(@RequestParam(name ="page", defaultValue = "1") Long page,
                                                              @RequestParam(name ="itemsPerPage", defaultValue = "20") Long itemsPerPage,
                                                              @RequestParam(name ="name", required = false) String medioPagoName){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageableRequest pageableRequest = new PageableRequest(username, page, itemsPerPage);
        PageableResponse<MedioPagoDTO, MedioPago> mediosDePago = medioPagoResourceBean.getMediosDePago(pageableRequest, medioPagoName);
        return mediosDePago;
    }

}
