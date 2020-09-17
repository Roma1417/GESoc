package utn.dds.tpAnual.db.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import utn.dds.tpAnual.db.entity.ubicacion.Ciudad;
import utn.dds.tpAnual.db.repository.CiudadRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CiudadServiceTest {

    @Autowired
    private CiudadService ciudadService;
    
    @Autowired
    private CiudadRepository ciudadRepository;

    private Ciudad unaCiudad = new Ciudad(null, "CiudadGenerica", "1");

    @Test
    public void persistenceTest() {
    	ciudadService.save(unaCiudad);
    	List<String> listaDeIDsAPI = new ArrayList<String>();
    	listaDeIDsAPI.add("1");
        List<Ciudad> ciudadesDeDB = ciudadRepository.getAllByIdAPI(listaDeIDsAPI);
        assertTrue(unaCiudad.getIdAPI().equals(ciudadesDeDB.get(0).getIdAPI()));
    }

}
