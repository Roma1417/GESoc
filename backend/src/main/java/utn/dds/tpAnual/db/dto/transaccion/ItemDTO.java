package utn.dds.tpAnual.db.dto.transaccion;

import utn.dds.tpAnual.db.dto.categoria.CategoriaDTO;
import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion.CriterioCategorizacion;
import utn.dds.tpAnual.db.entity.transaccion.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDTO extends StandardDTO<Item> {
    private Long id;
    private String descripcion;
    private List<CategoriaDTO> categorias;

    @Override
    public ItemDTO from(Item object){
        if (object == null) {
            return null;
        }
        List<Categoria> categorias = object.getCategorias();
        List<CategoriaDTO> categoriasDTO = new ArrayList<>();
        if (categorias != null){
            categorias.forEach(categoria -> {
                categoriasDTO.add(new CategoriaDTO().from(categoria));
            });
        }

        return new ItemDTO(object.getItemId(), object.getDescripcion(), categoriasDTO);
    }

    @Override
    public Item toEntity() {
        return new Item(descripcion);
    }

    public ItemDTO(){

    }

    public ItemDTO(Long id, String descripcion, List<CategoriaDTO> categorias) {
        this.id = id;
        this.descripcion = descripcion;
        this.categorias = categorias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
