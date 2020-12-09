package utn.dds.tpAnual.db.dto.pageable;

import org.springframework.data.domain.Page;
import utn.dds.tpAnual.db.dto.StandardDTO;

import java.util.List;

public class PageableResponse <DTOType extends StandardDTO, EntityType>{
    private Long total;
    private List<DTOType> data;

    public PageableResponse(){

    }

    public PageableResponse(Long total, List<DTOType> data) {
        this.total = total;
        this.data = data;
    }

    public PageableResponse<DTOType, EntityType> fromPage(Page<EntityType> pageEntity, DTOType parseEntity){
        this.data = parseEntity.fromList(pageEntity.toList());
        this.total = pageEntity.getTotalElements();
        return this;
    }

    public List<DTOType> getData() {
        return data;
    }

    public void setData(List<DTOType> data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
