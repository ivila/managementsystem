package com.zc.managementsystem.book.domain;

public class BookListParams extends BookQueryParams{
    int page;
    int pageSize;

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }
}
