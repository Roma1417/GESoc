package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.dds.tpAnual.db.entity.usuario.Usuario;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class CustomJPAService <T> {

    public abstract JpaRepository <T, Long> getRepository();

    public void save (T entity){
        getRepository().save(entity);
    }

    public void saveAll (Collection<T> entities){
        if (entities != null)
            getRepository().saveAll(entities);
    }

    public List<T> findAll (){
        return getRepository().findAll();
    }

    public List<T> findAllById (List<Long> ids) {
        return getRepository().findAllById(ids);
    }

    public boolean existsById(Long id) {
        return getRepository().findById(id).isPresent();
    }

    public void deleteAllInBatch (){
        getRepository().deleteAllInBatch();
    }

    public Optional<T> findById(Long id){
        return getRepository().findById(id);
    }
}
