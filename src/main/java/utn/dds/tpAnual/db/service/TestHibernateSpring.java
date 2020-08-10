package utn.dds.tpAnual.db.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import utn.dds.tpAnual.db.entity.TestEntity;
import utn.dds.tpAnual.db.entity.TestEntityMany;
import utn.dds.tpAnual.db.repository.TestEntityRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "utn.dds.tpAnual.db.service")
@EntityScan(basePackages = "utn.dds.tpAnual.db.entity")
@EnableJpaRepositories("utn.dds.tpAnual.db.repository")
public class TestHibernateSpring implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestEntityRepository repository;

    @Autowired
    private TestEntityService entityService;

    public static void main(String[] args) {
        SpringApplication.run(TestHibernateSpring.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        TestEntity testEntity = new TestEntity("Prueba", "Prueba");
        List<TestEntityMany> many = Arrays.asList(new TestEntityMany("Prueba", "Prueba", testEntity));
        testEntity.setMany(many);
        logger.info("Inserting -> {}", repository.save(testEntity));
        logger.info("All Tests -> {}", repository.findAll());

        //Query
        List<TestEntity> entityResultFull= repository.getTestEntitiesByNameWithMany("Prueba");
        List<TestEntityMany> manyResultFull = entityResultFull.get(0).getMany();

        //Query comun con error en init
        try {
            TestEntity entityResult = entityService.getFirstEntityByName("Prueba");
            if (entityResult != null) {
                List<TestEntityMany> manyResult = entityResult.getMany();
                logger.info("Name:" + manyResult.get(0).getName());
            }
        }catch(Exception ex){
            logger.info("Sigue fallando");
        }

    }
}