package utn.dds.tpAnual.db.entity.configuracion;

import utn.dds.tpAnual.db.entity.EntityInterface;

import javax.persistence.*;

@Entity
@Table(name = "CONFIGURACION")
public class Configuracion implements EntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long configuracionId;

    @Column(name = "CONFIGURACION_KEY", unique = true, nullable = false, length = 100)
    private String key;

    @Column(name = "CONFIGURACION_VALUE", nullable = false, length = 100)
    private String value;

    public Configuracion(){

    }

    public Configuracion(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Long getConfiguracionId() {
        return configuracionId;
    }

    public void setConfiguracionId(Long configuracionId) {
        this.configuracionId = configuracionId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public Long getId() {
        return configuracionId;
    }
}
