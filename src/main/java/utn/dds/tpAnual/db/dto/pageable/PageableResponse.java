package utn.dds.tpAnual.db.dto.pageable;

import java.util.List;

public class PageableResponse <T>{
    private Long total;
    private List<T> data;

    public PageableResponse(){

    }

    public PageableResponse(Long total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
