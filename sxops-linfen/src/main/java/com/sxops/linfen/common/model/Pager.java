package com.sxops.linfen.common.model;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * <p> Description: [分页扩展，可存放分页的数据]</p>
 * Created on: 2017/11/1 17:43
 *
 * @author <a href="mailto: liruifeng@sxops.com">尹归晋</a>
 * @version 1.0
 */
public class Pager<T>  {

    /**
     * 默认分页大小
     **/
    public static int DEFAULT_PAGE_SIZE = 20;

    /**
     * 默认当前页数
     **/
    public static int DEFAULT_PAGE_NUM = 1;

    public Pager() {
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.pageNum = DEFAULT_PAGE_NUM;
    }

    /**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;

    /**
     * 总数
     */
    private long total;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 数据记录
     **/
    private List<T> records;


    /**
     * <p>Discription: [设置分页信息] </p>
     * Created on: 2017/11/2 13:05
     * @param pageInfo 分页插件返回的分页信息
     * @author [尹归晋]
     */
    public void setPageInfo(Page pageInfo){
        this.total = pageInfo.getTotal();
        this.pages = pageInfo.getPages();
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
