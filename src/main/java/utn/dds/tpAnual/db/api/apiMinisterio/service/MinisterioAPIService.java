package utn.dds.tpAnual.db.api.apiMinisterio.service;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.api.apiMinisterio.dto.ProvinciasDTO;
import utn.dds.tpAnual.db.api.apiMinisterio.interfaces.GetGeographicalMinisterioAPI;

import java.io.IOException;

@Service
public class MinisterioAPIService implements GetGeographicalMinisterioAPI {
    private final String URL_MINISTERIO = "https://apis.datos.gob.ar/georef/api";
    private final String PROVINCIAS_ENDPOINT = "/provincias?";
    private final Gson gson = new Gson();

    @Override
    public ProvinciasDTO getProvincias() {
        String json = get(URL_MINISTERIO + PROVINCIAS_ENDPOINT + "campos=id,nombre");
        ProvinciasDTO provincias = gson.fromJson(json, ProvinciasDTO.class);
        return provincias;
    }
    @Override
    public ProvinciasDTO getProvincia(String nombre) {
        String json = get(URL_MINISTERIO + PROVINCIAS_ENDPOINT + "nombre=" + nombre);
        ProvinciasDTO provincia = gson.fromJson(json, ProvinciasDTO.class);
        return provincia;
    }
    

    private String get(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            try {
                HttpGet request = new HttpGet(url);
                CloseableHttpResponse response = httpClient.execute(request);
                try {
                    int statusCode = response.getStatusLine().getStatusCode();
                    HttpEntity entity = response.getEntity();
                    if (entity != null && statusCode == 200) {
                        result = EntityUtils.toString(entity);
                        return result;
                    }
                } finally {
                    response.close();
                }
            } finally {
                httpClient.close();
            }
        }
        catch (IOException e){
            result = null;
        }
        return result;
    }
}
