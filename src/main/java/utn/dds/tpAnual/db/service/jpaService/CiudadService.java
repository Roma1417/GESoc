package utn.dds.tpAnual.db.service.jpaService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import utn.dds.tpAnual.db.entity.ubicacion.Ciudad;
import utn.dds.tpAnual.db.repository.CiudadRepository;

@Service
public class CiudadService extends CustomJPAService<Ciudad> {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public JpaRepository<Ciudad, Long> getRepository() {
        return ciudadRepository;
    }

	public void guardarSiNoExiste(List<Ciudad> ciudadesAGuardar) {
		HashMap<String, Ciudad> ciudadMap = new HashMap<>();
        // Lleno el mapa con nombre pais y objeto
		ciudadesAGuardar.forEach(ciudad -> ciudadMap.put(ciudad.getIdAPI(), ciudad));
        Set<String> idsAPICiudades = ciudadMap.keySet();
        // Busco los paises en bd
        List<Ciudad> ciudadesEnBd = ciudadRepository.getAllByIdAPI(idsAPICiudades);
        // Quito del mapa los que esten bd
        ciudadesEnBd.forEach(ciudad -> ciudadMap.remove(ciudad.getIdAPI()));

        Collection<Ciudad> ciudadesRestantes = ciudadMap.values();
        ciudadRepository.saveAll(ciudadesRestantes);	
	}

	public Ciudad getPrimeraCiudadByIdAPI(String idAPI){
        List<Ciudad> ciudades = ciudadRepository.getAllByIdAPI(Arrays.asList(idAPI));
        return ciudades.isEmpty()? null : ciudades.get(0);
    }

    public List<Ciudad> getCiudadesByIdAPI(String idAPI){
        return ciudadRepository.getAllByIdAPI(Arrays.asList(idAPI));
    }

}

