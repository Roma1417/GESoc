package utn.dds.tpAnual.db.service.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.mongo.auditoria.TipoOperacion;
import utn.dds.tpAnual.db.mongo.entity.RegistroOperacion;
import utn.dds.tpAnual.db.service.mongo.repository.RegistroOperacionRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class RegistroOperacionService {

    @Autowired
    private RegistroOperacionRepository registroOperacionRepository;


    public Page<RegistroOperacion> getRegistroOperacionByTipo(PageableRequest pageableRequest, TipoOperacion tipoOperacion,
                                                              String nombreClase){
        if (tipoOperacion != null) {
            if (nombreClase != null) {
                return registroOperacionRepository
                        .findAllByTipoOperacionEqualsAndNombreClaseEquals(tipoOperacion,nombreClase, pageableRequest.toPageable());
            } else {
                return registroOperacionRepository.findAllByTipoOperacionEquals(tipoOperacion, pageableRequest.toPageable());
            }
        } else if (nombreClase != null) {
            return registroOperacionRepository.findAllByNombreClaseEquals(nombreClase, pageableRequest.toPageable());
        } else {
            return registroOperacionRepository.findAll(pageableRequest.toPageable());
        }

    }


    public void registrarAlta(Object object, String nombreClase) {
        RegistroOperacion registroOperacion = new RegistroOperacion(LocalDateTime.now(), TipoOperacion.ALTA, object, nombreClase);
        this.registrarOperacion(registroOperacion);
    }

    public void registrarModificacion(Object object, String nombreClase) {
        RegistroOperacion registroOperacion = new RegistroOperacion(LocalDateTime.now(), TipoOperacion.MODIFICACION, object, nombreClase);
        this.registrarOperacion(registroOperacion);
    }

    public void registrarBaja(Object object, String nombreClase) {
        RegistroOperacion registroOperacion = new RegistroOperacion(LocalDateTime.now(), TipoOperacion.BAJA, object, nombreClase);
        this.registrarOperacion(registroOperacion);
    }

    private void registrarOperacion(RegistroOperacion registroOperacion) {
        registroOperacionRepository.save(registroOperacion);
    }

    public List<RegistroOperacion> findAll(){
        return registroOperacionRepository.findAll();
    }

    public void deleteAll(){
        registroOperacionRepository.deleteAll();
    }

    public Collection<String> findAllNombresEntidad() {
        Set<String> nombresClase = new HashSet<>();
        registroOperacionRepository.findAll().stream().map(registroOperacion -> registroOperacion.getNombreClase())
                .forEach(nombresClase::add);
        return nombresClase;
    }
}
