package utn.dds.tpAnual.db.service.jpaService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import utn.dds.tpAnual.db.entity.ubicacion.Estado;
import utn.dds.tpAnual.db.repository.EstadoRepository;

@Service
public class EstadoService extends CustomJPAService<Estado> {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public JpaRepository<Estado, Long> getRepository() {
        return estadoRepository;
    }

	public void guardarSiNoExiste(List<Estado> estadosAGuardar) {
		HashMap<String, Estado> estadoMap = new HashMap<>();
        // Lleno el mapa con nombre pais y objeto
		estadosAGuardar.forEach(estado -> estadoMap.put(estado.getIdAPI(), estado));
        Set<String> idsAPIEstados = estadoMap.keySet();
        // Busco los paises en bd
        List<Estado> estadosEnBd = estadoRepository.getAllByIdAPI(idsAPIEstados);
        // Quito del mapa los que esten bd
        estadosEnBd.forEach(estado -> estadoMap.remove(estado.getIdAPI()));

        Collection<Estado> estadosRestantes = estadoMap.values();
        estadoRepository.saveAll(estadosRestantes);	
	}

	public Estado getPrimerEstadoByIdAPI(String idAPI){
        List<Estado> estados = estadoRepository.getAllByIdAPI(Arrays.asList(idAPI));
        return estados.isEmpty()? null : estados.get(0);
    }

    public List<Estado> getEstadosByIdAPI(String idAPI){
        return estadoRepository.getAllByIdAPI(Arrays.asList(idAPI));
    }

}
