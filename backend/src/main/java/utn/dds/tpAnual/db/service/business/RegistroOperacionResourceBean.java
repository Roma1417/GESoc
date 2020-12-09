package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.auditoria.RegistroOperacionDTO;
import utn.dds.tpAnual.db.dto.categoria.CategoriaDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.mongo.auditoria.TipoOperacion;
import utn.dds.tpAnual.db.mongo.entity.RegistroOperacion;
import utn.dds.tpAnual.db.service.jpaService.CategoriaService;
import utn.dds.tpAnual.db.service.mongo.service.RegistroOperacionService;

import java.util.Collection;
import java.util.List;

@Service
public class RegistroOperacionResourceBean {

    @Autowired
    private RegistroOperacionService registroOperacionService;

    public PageableResponse<RegistroOperacionDTO, RegistroOperacion> getRegistroOperacion(PageableRequest pageableRequest, TipoOperacion tipoOperacion,
                                                                                          String nombreCategoria) {
        Page<RegistroOperacion> operaciones = registroOperacionService
                .getRegistroOperacionByTipoAndNombreClase(pageableRequest, tipoOperacion, nombreCategoria);
        return new PageableResponse().fromPage(operaciones, new RegistroOperacionDTO());
    }

    public Collection<String> getNombreClases() {
        return registroOperacionService.findAllNombresEntidad();
    }
}
