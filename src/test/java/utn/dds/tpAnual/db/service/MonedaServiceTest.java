package utn.dds.tpAnual.db.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.repository.MonedaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonedaServiceTest {

    @Autowired
    private MonedaService monedaService;
    
    @Autowired
    private MonedaRepository monedaRepository;

    private Moneda unaMoneda = new Moneda("ARS", "Peso Argentino", "$");

    @Test
    public void persistenceTest() {
    	monedaService.save(unaMoneda);
    	List<String> listaDeIDsAPI = new ArrayList<String>();
    	listaDeIDsAPI.add("ARS");
        List<Moneda> monedasDeDB = monedaRepository.getAllByIdAPI(listaDeIDsAPI);
        assertTrue(unaMoneda.getIdAPI().equals(monedasDeDB.get(0).getIdAPI()));
    }

}