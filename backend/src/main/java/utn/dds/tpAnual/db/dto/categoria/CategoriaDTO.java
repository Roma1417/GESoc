package utn.dds.tpAnual.db.dto.categoria;

import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;

public class CategoriaDTO  extends StandardDTO<Categoria> {
    private Long id;
    private String descripcion;

    @Override
    public CategoriaDTO from(Categoria object) {
        return new CategoriaDTO(object.getIdCategoria(), object.getDescripcion());
    }

    @Override
    public Categoria toEntity() {
        return null;
    }

    public CategoriaDTO(){

    }

    public CategoriaDTO(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public CategoriaDTO(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
