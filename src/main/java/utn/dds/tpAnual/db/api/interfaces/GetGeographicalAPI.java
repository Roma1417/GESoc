package utn.dds.tpAnual.db.api.interfaces;

import utn.dds.tpAnual.db.api.dto.CiudadDTO;
import utn.dds.tpAnual.db.api.dto.EstadoDTO;
import utn.dds.tpAnual.db.api.dto.PaisDTO;

import java.util.List;

public interface GetGeographicalAPI {
    List<PaisDTO> getPaises();
    PaisDTO getPaisDetail(String id);
    EstadoDTO getEstadoDetail(String id);
    CiudadDTO getCiudadDetail(String id);
}
