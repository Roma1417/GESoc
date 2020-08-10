package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.TestEntity;
import utn.dds.tpAnual.db.entity.TestEntityMany;

import java.util.List;

@Repository
public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {

    @Query("SELECT T FROM TestEntity T WHERE T.name LIKE :nombre")
    List<TestEntity> getTestEntitiesByName(@Param("nombre") String n);

    @Query("SELECT T FROM TestEntity T " +
            " JOIN FETCH T.many m " +
            " WHERE T.name LIKE :nombre")
    List<TestEntity> getTestEntitiesByNameWithMany(@Param("nombre") String n);

}
