package com.spring.shop.utils;

public class SearchDto {

    private int pagePerCnt;
    private int curPage;
    private int limit;
    private int offset;

    public SearchDto() {
        // TODO Auto-generated constructor stub
    }

    public SearchDto(int pagePerCnt, int curPage, int limit, int offset) {
        super();
        this.pagePerCnt = pagePerCnt;
        this.curPage = curPage;
        this.limit = limit;
        this.offset = offset;
    }

    public int getPagePerCnt() {
        return pagePerCnt;
    }

    public void setPagePerCnt(int pagePerCnt) {
        this.pagePerCnt = pagePerCnt;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
