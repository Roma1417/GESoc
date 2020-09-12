package utn.dds.tpAnual.db.api.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.api.dto.CiudadDTO;
import utn.dds.tpAnual.db.api.dto.EstadoDTO;
import utn.dds.tpAnual.db.api.dto.MonedaDTO;
import utn.dds.tpAnual.db.api.dto.PaisDTO;
import utn.dds.tpAnual.db.api.interfaces.GetGeographicalAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MercadoLibreAPIService implements GetGeographicalAPI {

    private final String URL_MERCADO_LIBRE = "https://api.mercadolibre.com";
    private final String PAISES_ENDPOINT = "/classified_locations/countries/";
    private final String ESTADO_ENDPOINT = "/classified_locations/states/";
    private final String CIUDAD_ENDPOINT = "/classified_locations/cities/";
    private final String MONEDA_ENDPOINT = "/currencies/";
    private final Gson gson = new Gson();
    @Override
    public List<PaisDTO> getPaises() {
        String json = get(URL_MERCADO_LIBRE + PAISES_ENDPOINT);
        List<PaisDTO> paises = gson.fromJson(json, new TypeToken<List<PaisDTO>>(){}.getType());
        return paises;
    }
    
    @Override
    public PaisDTO getPaisDetail(String id) {
    	String json = get(URL_MERCADO_LIBRE + PAISES_ENDPOINT + id);
    	PaisDTO pais = gson.fromJson(json, PaisDTO.class);
    	return pais;
    }

    @Override
    public EstadoDTO getEstadoDetail(String id) {
        String json = get(URL_MERCADO_LIBRE + ESTADO_ENDPOINT + id);
        EstadoDTO estado = gson.fromJson(json, EstadoDTO.class);
        return estado;
    }

    @Override
    public CiudadDTO getCiudadDetail(String id) {
        String json = get(URL_MERCADO_LIBRE + CIUDAD_ENDPOINT + id);
        CiudadDTO ciudad = gson.fromJson(json, CiudadDTO.class);
        return ciudad;
    }

    @Override
    public List<MonedaDTO> getMonedas(){
        String json = get(URL_MERCADO_LIBRE + MONEDA_ENDPOINT);
        List<MonedaDTO> monedas = gson.fromJson(json, new TypeToken<List<MonedaDTO>>(){}.getType());
        return monedas;
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
