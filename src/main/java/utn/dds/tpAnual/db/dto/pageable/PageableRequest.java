package utn.dds.tpAnual.db.dto.pageable;

public class PageableRequest {
    private Long itemsPerPage;
    private Long page;

    public PageableRequest(){

    }

    public PageableRequest(Long itemsPerPage, Long page) {
        this.itemsPerPage = itemsPerPage;
        this.page = page;
    }

    public Long getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Long itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }
}
