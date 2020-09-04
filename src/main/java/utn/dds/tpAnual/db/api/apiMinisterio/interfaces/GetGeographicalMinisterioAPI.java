package utn.dds.tpAnual.db.api.apiMinisterio.interfaces;

import utn.dds.tpAnual.db.api.apiMinisterio.dto.ProvinciasDTO;

public interface GetGeographicalMinisterioAPI {
    ProvinciasDTO getProvincias();
    ProvinciasDTO getProvincia(String nombre);
}
