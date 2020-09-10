package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.repository.ActividadRepository;
import utn.dds.tpAnual.db.repository.PaisRepository;
import utn.dds.tpAnual.ubicacion.Pais;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class PaisService extends CustomJPAService<Pais> {

    @Autowired
    private PaisRepository paisRepository;

    @Override
    public JpaRepository<Pais, Long> getRepository() {
        return paisRepository;
    }


    public void guardarSiNoExiste(List<Pais> paisesAGuardar) {
        HashMap<String, Pais> paisMap = new HashMap<>();
        // Lleno el mapa con nombre pais y objeto
        paisesAGuardar.forEach(pais -> paisMap.put(pais.getDescripcion(), pais));
        Set<String> nombresPaises = paisMap.keySet();
        // Busco los paises en bd
        List<Pais> paisesEnBd = paisRepository.getAllByDescripciones(nombresPaises);
        // Quito del mapa los que esten bd
        paisesEnBd.forEach(pais -> paisMap.remove(pais.getDescripcion()));

        Collection<Pais> paisesRestantes = paisMap.values();
        paisRepository.saveAll(paisesRestantes);
    }
}
