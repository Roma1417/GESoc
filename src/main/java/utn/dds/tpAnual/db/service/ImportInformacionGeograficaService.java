package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.api.dto.CiudadDTO;
import utn.dds.tpAnual.db.api.dto.EstadoDTO;
import utn.dds.tpAnual.db.api.dto.MonedaDTO;
import utn.dds.tpAnual.db.api.dto.PaisDTO;
import utn.dds.tpAnual.db.api.service.MercadoLibreAPIService;
import utn.dds.tpAnual.db.entity.ubicacion.Ciudad;
import utn.dds.tpAnual.db.entity.ubicacion.Moneda;
import utn.dds.tpAnual.db.service.EstadoService;
import utn.dds.tpAnual.db.service.PaisService;
import utn.dds.tpAnual.db.entity.ubicacion.Estado;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportInformacionGeograficaService {

    @Autowired
    private MercadoLibreAPIService informacionGeograficaService;

    @Autowired
    private PaisService paisService;
    
    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CiudadService ciudadService;

    @Autowired
    private MonedaService monedaService;

    public void importPaises(){
        List<PaisDTO> paisDTOList = informacionGeograficaService.getPaises();
        List<Pais> paisArrayList = PaisDTO.toPaises(paisDTOList);
        paisService.guardarSiNoExiste(paisArrayList);
    }

    public void importEstados() {
        List<Pais> paises = paisService.findAll();
        paises.forEach(pais -> importarEstadosDe(pais));
    }

    private void importarEstadosDe(Pais pais){
        PaisDTO paisDTO = informacionGeograficaService.getPaisDetail(pais.getIdAPI());
        List<EstadoDTO> estadosAGuardar = paisDTO.getStates();
        List<Estado> estados = estadosAGuardar.stream()
                .map(estadoDTO -> estadoDTO.toEstado())
                .collect(Collectors.toList());
        // guardo si no existe para estado
        estados.forEach(estado -> estado.setPais(pais));
        estadoService.guardarSiNoExiste(estados);
    }

    public void importCiudades(){
        List<Estado> estados = estadoService.findAll();
        if (estados != null){
            estados.forEach(estado -> importarCiudadesDe(estado));
        }
    }

    private void importarCiudadesDe(Estado estado){
        EstadoDTO estadoDTO = informacionGeograficaService.getEstadoDetail(estado.getIdAPI());
        List<CiudadDTO> ciudadesAGuardar = estadoDTO.getCities();
        List<Ciudad> ciudades = ciudadesAGuardar.stream()
                .map(ciudadDTO -> ciudadDTO.toCiudad())
                .collect(Collectors.toList());
        ciudades.forEach(ciudad -> ciudad.setEstado(estado));
        ciudadService.guardarSiNoExiste(ciudades);
    }

    public void importMonedas(){
        List<MonedaDTO> monedaDTOList = informacionGeograficaService.getMonedas();
        List<Moneda> monedaArrayList = MonedaDTO.toMonedas(monedaDTOList);
        monedaService.guardarSiNoExiste(monedaArrayList);
    }

}
