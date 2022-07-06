package com.wuzhicheng.pigstore.common;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-03 17:00
 */
public class Page<T> {
    private List<T> records;
    private Long total;

    public static <T> Page<T> getPage(List<T> eles, Long total){
        Page<T> tPage = new Page<>();
        tPage.setRecords(eles);
        tPage.setTotal((long) eles.size());
        tPage.setTotal(total);
        return tPage;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
