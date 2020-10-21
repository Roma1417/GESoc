package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.entidad.EntidadDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.dto.usuario.MensajeDTO;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.usuario.Mensaje;
import utn.dds.tpAnual.db.service.jpaService.EntidadService;
import utn.dds.tpAnual.db.service.jpaService.MensajeService;

@Service
public class EntidadResourceBean {
    @Autowired
    private EntidadService entidadService;

    public PageableResponse<EntidadDTO, Entidad> getEntidades(PageableRequest pageableRequest,
                                                              String usuario, String entidadName) {
        Page<Entidad> entidades = entidadService.getEntidadesRelated(usuario, entidadName, pageableRequest);
        return new PageableResponse().fromPage(entidades, new EntidadDTO());
    }
}
