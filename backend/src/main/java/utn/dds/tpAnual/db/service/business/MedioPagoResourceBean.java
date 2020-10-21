package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.transaccion.MedioPagoDTO;
import utn.dds.tpAnual.db.dto.usuario.MensajeDTO;
import utn.dds.tpAnual.db.entity.transaccion.MedioPago;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.service.jpaService.MedioPagoService;
import utn.dds.tpAnual.db.service.jpaService.MensajeService;

@Service
public class MedioPagoResourceBean {
    @Autowired
    private MedioPagoService medioPagoService;

    public PageableResponse<MedioPagoDTO, MedioPago> getMediosDePago(PageableRequest pageableRequest, String medioPagoName) {
        Page<MedioPago> mediosDePago = medioPagoService.getMediosDePagoByNameLike(pageableRequest, medioPagoName);
        return new PageableResponse().fromPage(mediosDePago, new MedioPagoDTO());
    }
}
