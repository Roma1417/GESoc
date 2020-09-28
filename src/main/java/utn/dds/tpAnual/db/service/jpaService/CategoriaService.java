package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.repository.CategoriaRepository;

@Service
public class CategoriaService extends CustomJPAService<Categoria> {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public JpaRepository<Categoria, Long> getRepository() {
        return categoriaRepository;
    }

    public Categoria getCategoriaByDescripcion(String descripcion){
        Categoria categoria = categoriaRepository.getCategoriaByDescripcion(descripcion);
        return categoria;
    }
}
