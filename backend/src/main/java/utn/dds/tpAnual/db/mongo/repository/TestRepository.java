package utn.dds.tpAnual.db.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import utn.dds.tpAnual.db.entity.usuario.Usuario;

import java.util.List;

public interface TestRepository  extends MongoRepository<Usuario, String> {
    public List<Usuario> findAllByNombre(String nombre);

}
