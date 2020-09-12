package utn.dds.tpAnual.db.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.repository.PaisRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaisServiceTest {

    @Autowired
    private PaisService paisService;
    
    @Autowired
    private PaisRepository paisRepository;

    private Pais unPais = new Pais("PaisGenerico", "PG");

    @Test
    public void persistenceTest() {
    	paisService.save(unPais);
        List<Pais> paisesDeDB = paisRepository.getAllByDescripciones(Arrays.asList("PaisGenerico"));
        assertTrue(unPais.getIdAPI().equals(paisesDeDB.get(0).getIdAPI()));
    }

}