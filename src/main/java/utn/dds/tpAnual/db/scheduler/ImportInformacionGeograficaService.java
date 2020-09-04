package utn.dds.tpAnual.db.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.api.dto.PaisDTO;
import utn.dds.tpAnual.db.api.service.MercadoLibreAPIService;
import utn.dds.tpAnual.db.service.PaisService;
import utn.dds.tpAnual.ubicacion.Pais;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImportInformacionGeograficaService {

    @Autowired
    private MercadoLibreAPIService mercadoLibreAPIService;

    @Autowired
    private PaisService paisService;

    public void importPaises(){
        List<PaisDTO> paisDTOList = mercadoLibreAPIService.getPaises();
        List<Pais> paisArrayList = PaisDTO.toPaises(paisDTOList);
        paisService.saveAll(paisArrayList);
    }

}
