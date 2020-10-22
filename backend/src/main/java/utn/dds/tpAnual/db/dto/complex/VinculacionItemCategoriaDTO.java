package utn.dds.tpAnual.db.dto.complex;

public class VinculacionItemCategoriaDTO {
    private Long itemId;
    private Long categoriaId;

    public VinculacionItemCategoriaDTO(){

    }

    public VinculacionItemCategoriaDTO(Long itemId, Long categoriaId) {
        this.itemId = itemId;
        this.categoriaId = categoriaId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
