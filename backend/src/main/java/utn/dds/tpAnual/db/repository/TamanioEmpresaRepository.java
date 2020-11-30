package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.afip.Actividad;
import utn.dds.tpAnual.db.entity.afip.tamanios.TamanioEmpresa;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;

import java.util.List;

@Repository
public interface TamanioEmpresaRepository extends JpaRepository<TamanioEmpresa, Long> {

    @Query("SELECT t FROM TamanioEmpresa t WHERE t.nombre LIKE :nombre")
    TamanioEmpresa getTamanioByNombre(@Param("nombre") String nombre);
}
