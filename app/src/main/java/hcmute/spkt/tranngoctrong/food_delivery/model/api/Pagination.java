package hcmute.spkt.tranngoctrong.food_delivery.model.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import hcmute.spkt.tranngoctrong.food_delivery.model.deserializer.PaginationDeserializer;

@JsonDeserialize(using = PaginationDeserializer.class)
public class Pagination {

    private int count;
    private int pageSize;
    private int pageIndex;
    private int pageCount;

    public Pagination() {

    }

    public Pagination(int count, int pageSize, int pageIndex, int pageCount) {
        this.count = count;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.pageCount = pageCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
