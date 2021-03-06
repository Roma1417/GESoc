package utn.dds.tpAnual.db.service.jpaService;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import utn.dds.tpAnual.db.entity.ubicacion.Estado;
import utn.dds.tpAnual.db.repository.EstadoRepository;
import utn.dds.tpAnual.db.scheduler.ProgramadorDeTareas;
import utn.dds.tpAnual.db.service.jpaService.EstadoService;
import utn.dds.tpAnual.db.service.mongo.repository.RegistroOperacionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean({ProgramadorDeTareas.class, RegistroOperacionRepository.class })
public class EstadoServiceTest {

    @Autowired
    private EstadoService estadoService;
    
    @Autowired
    private EstadoRepository estadoRepository;

    private Estado unEstado = new Estado(null, "EstadoGenerico", "1");

    @Test
    public void persistenceTest() {
    	estadoService.save(unEstado);
    	List<String> listaDeIDsAPI = new ArrayList<String>();
    	listaDeIDsAPI.add("1");
        List<Estado> estadosDeDB = estadoRepository.getAllByIdAPI(listaDeIDsAPI);
        assertTrue(unEstado.getIdAPI().equals(estadosDeDB.get(0).getIdAPI()));
    }

}

