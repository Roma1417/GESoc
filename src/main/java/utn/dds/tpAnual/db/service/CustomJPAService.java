package utn.dds.tpAnual.db.service;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CustomJPAService <T> {

    public abstract JpaRepository <T, Long> getRepository();

    public void save (T entity){
        getRepository().save(entity);
    }

}
