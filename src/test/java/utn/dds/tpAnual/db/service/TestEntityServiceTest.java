package utn.dds.tpAnual.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utn.dds.tpAnual.db.entity.TestEntity;
import utn.dds.tpAnual.db.entity.TestEntityMany;
import utn.dds.tpAnual.db.repository.TestEntityRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEntityServiceTest {

    @Autowired
    private TestEntityService entityService;

    @Autowired
    private TestEntityRepository testEntityRepository;

    @Test
    public void persistenceTest() {
        TestEntity testEntity = new TestEntity("Test", "Test");
        testEntityRepository.save(testEntity);
        TestEntity resultEntity = entityService.getFirstEntityByName("Test");
        assertEquals(testEntity.getEmployeeId(), resultEntity.getEmployeeId());
    }

}