package utn.dds.tpAnual.db.configuracion;

import utn.dds.tpAnual.db.entity.configuracion.Configuracion;

public enum ConfiguracionEnum {

    DIAS_ACEPTABLE_VINCULACION("DIAS_ACEPTABLE_VINCULACION", "30"),
    LONGITUD_CONTRASENIA("LONGITUD_CONTRASENIA", "8"),
    MENSAJE_CORRECTO("MENSAJE_CORRECTO", "Validacion realizada con Exito"),
    MENSAJE_ERRONEO("MENSAJE_ERRONEO", "Fallo de Validacion"),
    ASUNTO_INICIO("ASUNTO_INICIO", "Resultado Validacion Egreso: "),
    CANTIDAD_INTENTOS_LOGIN("CANTIDAD_INTENTOS_LOGIN", "3");


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
