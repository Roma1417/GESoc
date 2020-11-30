package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.transaccion.DocumentoComercial;
import utn.dds.tpAnual.db.repository.DocumentoComercialRepository;

import java.util.List;

@Service
public class DocumentoComercialService extends CustomJPAService<DocumentoComercial> {

    @Autowired
    private DocumentoComercialRepository documentoComercialRepository;

    @Override
    public JpaRepository<DocumentoComercial, Long> getRepository() {
        return documentoComercialRepository;
    }

    public DocumentoComercial getFirstDocumentoComercialByNumero(int numero){

        List<DocumentoComercial> documentosComerciales = documentoComercialRepository.getDocumentoComercialByNumero(numero);
        return documentosComerciales.isEmpty() ? null : documentosComerciales.get(0);

    }
}
