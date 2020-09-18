package utn.dds.tpAnual.db.entity.configuracion;

import utn.dds.tpAnual.db.configuracion.ConfiguracionEnum;

import java.util.HashMap;
import java.util.Map;

public class ConfiguracionCache {

    private Map<String, String> configuracionesCacheadas = new HashMap<>();
    private static ConfiguracionCache instance;

    public static ConfiguracionCache getInstance(){
        if (instance == null){
            instance = new ConfiguracionCache();
        }
        return instance;
    }

    private ConfiguracionCache(){

    }

    public static String getValue(ConfiguracionEnum configuracionEnum){
        return instance.configuracionesCacheadas.get(configuracionEnum.getKey());
    }


    public static void addToCache(Configuracion configuracion){
        instance.configuracionesCacheadas.put(configuracion.getKey(), configuracion.getValue());
    }

    public static void cleanCache(){
        instance.configuracionesCacheadas.clear();
    }
}
