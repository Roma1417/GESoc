package utn.dds.tpAnual.db.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

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

    public boolean existsById(Long id) {
        return getRepository().findById(id).isPresent();
    }

    public void deleteAllInBatch (){
        getRepository().deleteAllInBatch();
    }
}
