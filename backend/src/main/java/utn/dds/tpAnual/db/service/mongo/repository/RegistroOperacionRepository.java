package utn.dds.tpAnual.db.service.mongo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import utn.dds.tpAnual.db.mongo.auditoria.TipoOperacion;
import utn.dds.tpAnual.db.mongo.entity.RegistroOperacion;

import java.util.List;

public interface RegistroOperacionRepository extends MongoRepository<RegistroOperacion, String> {

    Page<RegistroOperacion> findAllByTipoOperacionEqualsAndNombreClaseEquals(TipoOperacion tipoOperacion,
                                                                              String nombreClase, Pageable pageable);

    Page<RegistroOperacion> findAllByTipoOperacionEquals(TipoOperacion tipoOperacion, Pageable pageable);

    Page<RegistroOperacion> findAllByNombreClaseEquals(String nombreClase, Pageable pageable);

    Page<RegistroOperacion> findAll(Pageable pageable);

}
