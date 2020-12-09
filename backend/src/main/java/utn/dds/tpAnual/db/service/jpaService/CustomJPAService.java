package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.EntityInterface;
import utn.dds.tpAnual.db.service.mongo.service.RegistroOperacionService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public abstract class CustomJPAService <T> {

    @Autowired
    private RegistroOperacionService registroOperacionService;


    @PersistenceContext
    private EntityManager entityManager;

    public abstract JpaRepository <T, Long> getRepository();

    public void save (T entity){
        Long id = ((EntityInterface) entity).getId();
        if (id != null && (entityManager.contains(entity) || entityManager.find(entity.getClass(), id) != null)){
            registroOperacionService.registrarModificacion(getEntity(entity), entity.getClass().getSimpleName());
        } else {
            registroOperacionService.registrarAlta(getEntity(entity), entity.getClass().getSimpleName());
        }
        getRepository().save(entity);
    }

    public void delete(T entity){
        getRepository().delete(entity);
        registroOperacionService.registrarBaja(getEntity(entity), entity.getClass().getSimpleName());
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

    public boolean puedePersistirseEnMongo(){
        return true;
    }

    public Object getEntity(Object entity) {
        return puedePersistirseEnMongo() ? entity : "Objeto no persistible por recursividad";
    }
}
