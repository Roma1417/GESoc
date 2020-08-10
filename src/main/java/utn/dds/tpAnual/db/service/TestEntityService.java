package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.TestEntity;
import utn.dds.tpAnual.db.repository.TestEntityRepository;

import java.util.List;

@Service
public class TestEntityService {

    @Autowired
    private TestEntityRepository testEntityRepository;

    public TestEntity getFirstEntityByName (String name){
        List<TestEntity> testEntityList = testEntityRepository.getTestEntitiesByName(name);
        return testEntityList.isEmpty() ? null : testEntityList.get(0);
    }

}
