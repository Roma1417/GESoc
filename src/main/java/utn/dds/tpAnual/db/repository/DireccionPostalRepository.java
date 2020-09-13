package utn.dds.tpAnual.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;
import utn.dds.tpAnual.db.entity.ubicacion.Estado;

import java.util.Collection;
import java.util.List;

@Repository
public interface DireccionPostalRepository extends JpaRepository<DireccionPostal, Long> {



}
