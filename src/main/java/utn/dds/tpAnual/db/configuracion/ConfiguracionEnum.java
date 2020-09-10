package utn.dds.tpAnual.db.configuracion;

import utn.dds.tpAnual.db.entity.configuracion.Configuracion;

public enum ConfiguracionEnum {

    DIAS_ACEPTABLE_VINCULACION("DIAS_ACEPTABLE_VINCULACION", "30");

    private String key;
    private String defaultValue;

    ConfiguracionEnum(String key, String defaultValue){
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
