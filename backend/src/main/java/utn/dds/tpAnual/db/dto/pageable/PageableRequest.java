package utn.dds.tpAnual.db.dto.pageable;

import org.springframework.data.domain.PageRequest;

public class PageableRequest {
    private String user;
    private Long itemsPerPage;
    private Long page;

    public PageableRequest(){

    }

    public PageRequest toPageable () {
        return PageRequest.of(getPage().intValue() - 1, getItemsPerPage().intValue());
    }

    public PageableRequest(String user, Long page, Long itemsPerPage) {
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
