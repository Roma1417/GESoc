package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.transaccion.DocumentoComercial;

import java.util.List;

@Repository
public interface DocumentoComercialRepository extends JpaRepository<DocumentoComercial, Long> {

    @Query("SELECT d FROM DocumentoComercial d WHERE d.numero = :numero")
    List<DocumentoComercial> getDocumentoComercialByNumero(@Param("numero") int numero);

}
