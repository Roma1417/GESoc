package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.ubicacion.MonedaDTO;
import utn.dds.tpAnual.db.dto.ubicacion.PaisDTO;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.service.jpaService.MonedaService;

@Service
public class MonedaResourceBean {

    @Autowired
    private MonedaService monedaService;

    public PageableResponse<MonedaDTO, Moneda> getMonedas(PageableRequest pageableRequest, String monedaName) {
        Page<Moneda> monedas = monedaService.getMonedas(pageableRequest, monedaName);
        return new PageableResponse().fromPage(monedas, new MonedaDTO());
    }
}
