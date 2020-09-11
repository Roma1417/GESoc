package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.configuracion.ConfiguracionEnum;
import utn.dds.tpAnual.db.entity.configuracion.Configuracion;
import utn.dds.tpAnual.db.entity.ubicacion.Pais;
import utn.dds.tpAnual.db.repository.ConfiguracionRepository;
import utn.dds.tpAnual.db.repository.PaisRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class ConfiguracionService extends CustomJPAService<Configuracion> {

    @Autowired
    private ConfiguracionRepository configuracionRepository;

    @Override
    public JpaRepository<Configuracion, Long> getRepository() {
        return configuracionRepository;
    }


    public String getValue(ConfiguracionEnum configuracionEnum){
        Configuracion configuracion = configuracionRepository.getConfiguracionByKey(configuracionEnum.getKey());
        if (configuracion == null){
            configuracion = new Configuracion(configuracionEnum.getKey(), configuracion.getValue());
            configuracionRepository.save(configuracion);
        }
        return configuracion.getValue();
    }

    public Integer getIntValue(ConfiguracionEnum configuracionEnum){
        return Integer.valueOf(getValue(configuracionEnum));
    }
}
