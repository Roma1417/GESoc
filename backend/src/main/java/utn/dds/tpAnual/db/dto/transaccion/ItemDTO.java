package utn.dds.tpAnual.db.dto.transaccion;

import utn.dds.tpAnual.db.dto.categoria.CategoriaDTO;
import utn.dds.tpAnual.db.dto.StandardDTO;
import utn.dds.tpAnual.db.entity.categorizacion.categoria.Categoria;
import utn.dds.tpAnual.db.entity.categorizacion.criterioCategorizacion.CriterioCategorizacion;
import utn.dds.tpAnual.db.entity.transaccion.Item;

public class ItemDTO extends StandardDTO<Item> {
    private Long id;
    private Long criterioCategorizacionId;
    private String descripcion;
    private CategoriaDTO categoria;

    @Override
    public ItemDTO from(Item object){
        CriterioCategorizacion criterioCategorizacion = object.getPrimerCriterio();
        Long idCriterio = criterioCategorizacion != null ? criterioCategorizacion.getCriterioCategorizacionId() : null;
        Categoria categoria = object.getCategoria();
        CategoriaDTO categoriaDTO = categoria != null ? new CategoriaDTO().from(categoria) : null;
        return new ItemDTO(object.getItemId(), idCriterio, object.getDescripcion(), categoriaDTO);
    }

    @Override
    public Item toEntity() {
        return new Item(descripcion);
    }

    public ItemDTO(){

    }

    public ItemDTO(Long id, Long criterioCategorizacionId, String descripcion, CategoriaDTO categoria) {
        this.id = id;
        this.criterioCategorizacionId = criterioCategorizacionId;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public Long getCriterioCategorizacionId() {
        return criterioCategorizacionId;
    }

    public void setCriterioCategorizacionId(Long criterioCategorizacionId) {
        this.criterioCategorizacionId = criterioCategorizacionId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
