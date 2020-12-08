package utn.dds.tpAnual.db.service.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.mongo.auditoria.TipoOperacion;
import utn.dds.tpAnual.db.mongo.entity.RegistroOperacion;
import utn.dds.tpAnual.db.service.mongo.repository.RegistroOperacionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroOperacionService {

    @Autowired
    private RegistroOperacionRepository registroOperacionRepository;


    public void registrarAlta(Object object) {
        RegistroOperacion registroOperacion = new RegistroOperacion(LocalDateTime.now(), TipoOperacion.ALTA, object);
        this.registrarOperacion(registroOperacion);
    }

    public void registrarModificacion(Object object) {
        RegistroOperacion registroOperacion = new RegistroOperacion(LocalDateTime.now(), TipoOperacion.MODIFICACION, object);
        this.registrarOperacion(registroOperacion);
    }

    public void registrarBaja(Object object) {
        RegistroOperacion registroOperacion = new RegistroOperacion(LocalDateTime.now(), TipoOperacion.BAJA, object);
        this.registrarOperacion(registroOperacion);
    }

    private void registrarOperacion(RegistroOperacion registroOperacion) {
        registroOperacionRepository.save(registroOperacion);
    }

    public List<RegistroOperacion> findAll(){
        return registroOperacionRepository.findAll();
    }
}
