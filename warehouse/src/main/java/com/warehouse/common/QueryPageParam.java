package com.warehouse.common;

import java.util.HashMap;

public class QueryPageParam {
    private static int PAGE_SIZE = 20;
    private static int PAGE_NUM =10;

    private int pageSize = PAGE_SIZE;
    private int pageNum = PAGE_NUM;

    private HashMap param;

//    public static int getPageSize() {
//        return PAGE_SIZE;
//    }
//
//    public static void setPageSize(int pageSize) {
//        PAGE_SIZE = pageSize;
//    }
//
//    public static int getPageNum() {
//        return PAGE_NUM;
//    }
//
//    public static void setPageNum(int pageNum) {
//        PAGE_NUM = pageNum;
//    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public HashMap getParam() {
        return param;
    }

    public void setParam(HashMap param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "QueryPageParam{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", param=" + param +
                '}';
    }
}
