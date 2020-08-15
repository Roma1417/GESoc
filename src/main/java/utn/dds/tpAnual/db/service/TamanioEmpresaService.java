package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.afip.tamanios.TamanioEmpresa;
import utn.dds.tpAnual.db.repository.TamanioEmpresaRepository;

import java.util.List;

@Service
public class TamanioEmpresaService extends CustomJPAService<TamanioEmpresa> {

    @Autowired
    private TamanioEmpresaRepository tamanioEmpresaRepository;

    @Override
    public JpaRepository<TamanioEmpresa, Long> getRepository() {
        return tamanioEmpresaRepository;
    }

    public TamanioEmpresa getTamanioSameNombre(String nombre){
        TamanioEmpresa tamanioEmpresa = tamanioEmpresaRepository.getTamanioByNombre(nombre);
        return tamanioEmpresa;
    }
}
