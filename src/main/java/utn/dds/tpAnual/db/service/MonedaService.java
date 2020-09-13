package utn.dds.tpAnual.db.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.repository.MonedaRepository;

@Service
public class MonedaService extends CustomJPAService<Moneda>{

	@Autowired
    private MonedaRepository monedaRepository;

    @Override
    public JpaRepository<Moneda, Long> getRepository() {
        return monedaRepository;
    }

    public void guardarSiNoExiste(List<Moneda> monedasAGuardar) {
        HashMap<String, Moneda> monedaMap = new HashMap<>();
        // Lleno el mapa con nombre pais y objeto
        monedasAGuardar.forEach(moneda -> monedaMap.put(moneda.getIdAPI(), moneda));
        Set<String> idsMonedas = monedaMap.keySet();
        // Busco los paises en bd
        List<Moneda> monedasEnBd = monedaRepository.getAllByIdAPI(idsMonedas);
        // Quito del mapa los que esten bd
        monedasEnBd.forEach(moneda -> monedaMap.remove(moneda.getIdAPI()));

        Collection<Moneda> monedasRestantes = monedaMap.values();
        monedaRepository.saveAll(monedasRestantes);
    }

    public Moneda getPrimerMonedaByIdAPI(String idAPI){
        List<Moneda> monedas = monedaRepository.getAllByIdAPI(Arrays.asList(idAPI));
        return monedas.isEmpty()? null : monedas.get(0);
    }

    public List<Moneda> getMonedasByIdAPI(String idAPI){
        return monedaRepository.getAllByIdAPI(Arrays.asList(idAPI));
    }

}
