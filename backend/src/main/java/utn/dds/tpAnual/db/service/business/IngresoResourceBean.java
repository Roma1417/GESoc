package utn.dds.tpAnual.db.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.transaccion.EgresoDTO;
import utn.dds.tpAnual.db.dto.transaccion.IngresoDTO;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.dto.pageable.PageableResponse;
import utn.dds.tpAnual.db.entity.entidad.Entidad;
import utn.dds.tpAnual.db.entity.transaccion.DocumentoComercial;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.entity.transaccion.Ingreso;
import utn.dds.tpAnual.db.entity.transaccion.Presupuesto;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.entity.usuario.Usuario;
import utn.dds.tpAnual.db.service.jpaService.*;
import utn.dds.tpAnual.db.service.rules.IngresoRules;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IngresoResourceBean {

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private MonedaService monedaService;

    @Autowired
    private EntidadService entidadService;

    @Autowired
    private UsuarioService usuarioService;

    public PageableResponse<IngresoDTO, Ingreso> getIngresos(PageableRequest pageableRequest, String username) {
        Page<Ingreso> ingresos = ingresoService.findAllRelated(pageableRequest, username);
        return new PageableResponse().fromPage(ingresos, new IngresoDTO());
    }

    public IngresoDTO crearIngreso(IngresoDTO ingresoDTO, String username){
        Ingreso ingreso = ingresoDTO.toEntity();
        Usuario usuario = usuarioService.getUsuarioByUsername(username);
        Optional<Entidad> entidadRealizadora = entidadService.findAllRelated(usuario, ingresoDTO.getEntidadRealizadora().getIdEntidad());
        Optional<Pais> pais = paisService.findById(ingresoDTO.getDocumentoComercial().getPais().getPaisId());
        Optional<Moneda> moneda = monedaService.findById(ingresoDTO.getDocumentoComercial().getMoneda().getMonedaId());
        DocumentoComercial documentoComercial = ingresoDTO.getDocumentoComercial().toEntity(pais.get(), moneda.get());

        IngresoRules.getInstance().validarCrearIngreso(entidadRealizadora, documentoComercial, ingreso, pais, moneda);

        ingreso.setEntidadRealizadora(entidadRealizadora.get());
        ingreso.setDocumentoComercial(documentoComercial);
        ingresoService.save(ingreso);
        ingresoDTO.setIdIngreso(ingreso.getOperacionId());
        return ingresoDTO;
    }

    public PageableResponse<IngresoDTO, Ingreso> getIngresosById(PageableRequest pageableRequest, String username, Long ingresoId) {
        Page<Ingreso> ingresos = ingresoService.findAllRelatedById(pageableRequest, username, ingresoId);
        return new PageableResponse().fromPage(ingresos, new IngresoDTO());
    }
}
