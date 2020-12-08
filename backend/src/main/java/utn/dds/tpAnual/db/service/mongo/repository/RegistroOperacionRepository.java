package utn.dds.tpAnual.db.service.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import utn.dds.tpAnual.db.mongo.entity.RegistroOperacion;

public interface RegistroOperacionRepository extends MongoRepository<RegistroOperacion, String> {


}
