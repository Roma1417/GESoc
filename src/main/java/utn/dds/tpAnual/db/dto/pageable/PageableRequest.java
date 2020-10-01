package utn.dds.tpAnual.db.dto.pageable;

public class PageableRequest {
    private String user;
    private Long itemsPerPage;
    private Long page;

    public PageableRequest(){

    }

    public PageableRequest(String user, Long itemsPerPage, Long page) {
        this.user = user;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
