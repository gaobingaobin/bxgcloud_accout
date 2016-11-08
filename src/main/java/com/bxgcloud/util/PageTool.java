package com.bxgcloud.util;

import java.util.List;

/**
 * 分页类
 */
public class PageTool {
    private int total;//总页数
    private int page;//当前页数
    private int records;//总记录数
    private List rows;//结果集

    public PageTool() {
    }

    public PageTool(int currentPage, int totalRows, int pageSize, List results) {
        this.rows = results;
        this.records = totalRows;
        this.page = currentPage;
        if ((totalRows % pageSize) == 0) {
            this.total = totalRows / pageSize;
        } else {
            this.total = totalRows / pageSize + 1;
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageTool{" +
                "total=" + total +
                ", page=" + page +
                ", records=" + records +
                ", rows=" + rows +
                '}';
    }
}
