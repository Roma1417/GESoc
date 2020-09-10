package utn.dds.tpAnual.db.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.api.dto.EstadoDTO;
import utn.dds.tpAnual.db.api.dto.PaisDTO;
import utn.dds.tpAnual.db.api.service.MercadoLibreAPIService;
import utn.dds.tpAnual.db.service.PaisService;
import utn.dds.tpAnual.db.entity.ubicacion.Estado;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportInformacionGeograficaService {

    @Autowired
    private MercadoLibreAPIService informacionGeograficaService;

    @Autowired
    private PaisService paisService;

    public void importPaises(){
        List<PaisDTO> paisDTOList = informacionGeograficaService.getPaises();
        List<Pais> paisArrayList = PaisDTO.toPaises(paisDTOList);
        paisService.guardarSiNoExiste(paisArrayList);
    }

    public void importProvincias() {
        List<Pais> paises = paisService.findAll();
        paises.forEach(pais -> {
            PaisDTO paisDTO = informacionGeograficaService.getPaisDetail(pais.getIdAPI());
            List<EstadoDTO> estadosAGuardar = paisDTO.getStates();
            // paso a clase estado
            List<Estado> estados = estadosAGuardar.stream()
                    .map(estadoDTO -> estadoDTO.toEstado())
                    .collect(Collectors.toList());
            // guardo si no existe para estado
        });
    }
}
