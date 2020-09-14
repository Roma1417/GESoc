package utn.dds.tpAnual.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.proveedor.Proveedor;
import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;
import utn.dds.tpAnual.db.repository.ProveedorRepository;

import java.util.List;

@Service
public class ProveedorService extends CustomJPAService<Proveedor> {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public JpaRepository<Proveedor, Long> getRepository() {
        return proveedorRepository;
    }

    public Proveedor getFirstProveedorByDireccionPostal(DireccionPostal dp){

        List<Proveedor> proveedores = proveedorRepository.getProveedorByDireccionPostal(dp);
        return proveedores.isEmpty() ? null : proveedores.get(0);

    }
}
