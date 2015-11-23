package com.qp.common.base;


import java.io.Serializable;


/**
 * @author haiping
 * 增加分页字段
 */
@SuppressWarnings("unused")
public class PaginateBaseDO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** 默认每页的记录数量*/
    public static final int PAGESIZE_DEFAULT = 20;
    
    /**每页大小*/
    private int pageSize = PAGESIZE_DEFAULT;
    
    /** 当前页,第一页是1*/
    private int index = 1;
    
    /** 总记录数*/
    private int totalItem;
    
    /**总页数*/
    private int totalPage;

    /** 分页后的记录开始的地方, 第一条记录是1*/
	private int startRow;
    
    /**分页后的记录结束的地方*/
    private int endRow;

    /**表id**/
    private Integer tableId;
    
    /**排序字段**/
    private String orderField;
    
    /**升序 还是 降序,true为升序，false为降序*/
    private Boolean isAsc;
    
    /**
     * 表示是不是第一页
     * @return true 是; false 不是
     */
    public boolean isFirstPage(){
          return index <= 1;
    }

    public boolean isMiddlePage() {
         return !(isFirstPage() || isLastPage());
    }

    public boolean isLastPage() {
        return index >= totalPage;
    }

    public boolean isNextPageAvailable() {
        return !isLastPage();
    }

    public boolean isPreviousPageAvailable() {
          return !isFirstPage();
    }

    /**
     * 下一页号
     * @return 取得下一页号
     */
    public int getNextPage() {
        if(isLastPage()) {
            return totalItem;
        } else {
            return index+1;
        }
    }

    public int getPreviousPage() {
        if(isFirstPage()) {
            return 1;
        } else {
            return index - 1;
        }
    }
    
    /**
     * Method getPageSize returns the pageSize of this PaginatedArrayList object.
     *  每页大小
     * @return the pageSize (type int) of this PaginatedArrayList object.
     */

    public int getPageSize() {
        return pageSize;
    }

    /**
     * Method setPageSize sets the pageSize of this PaginatedArrayList object.
     *  每页大小
     * @param pageSize the pageSize of this PaginatedArrayList object.
     */

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        repaginate();
    }

    /**
     * Method getIndex returns the index of this PaginatedArrayList object.
     *  当前页。第一页是1
     * @return the index (type int) of this PaginatedArrayList object.
     */

    public int getIndex() {
        return index;
    }

    /**
     * Method setIndex sets the index of this PaginatedArrayList object.
     *  当前页。第一页是1
     * @param index the index of this PaginatedArrayList object.
     *
     */

    public void setIndex(int index) {
        this.index = index;
        repaginate();
    }

    /**
     * Method getTotalItem returns the totalItem of this PaginatedArrayList object.
     *  总记录数
     * @return the totalItem (type int) of this PaginatedArrayList object.
     */

    public int getTotalItem() {
        return totalItem;
    }

    /**
     * Method setTotalItem sets the totalItem of this PaginatedArrayList object.
     *  总记录数
     * @param totalItem the totalItem of this PaginatedArrayList object.
     *
     */

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
        if( this.totalItem <= 0){
            totalPage = 0;
            index = 1;
            startRow = 0;
        }
        repaginate();
    }

    /**
     * Method getTotalPage returns the totalPage of this PaginatedArrayList object.
     *  总页数
     * @return the totalPage (type int) of this PaginatedArrayList object.
     */

    public int getTotalPage() {
        return totalPage;
    }

    /**
     * Method getStartRow returns the startRow of this PaginatedArrayList object.
     *  分页后的记录开始的地方
     * @return the startRow (type int) of this PaginatedArrayList object.
     */

    public int getStartRow() {
        return (index - 1) * pageSize;
    }
    
    /**
     * Method getEndRow returns the endRow of this PaginatedArrayList object.
     *  分页后的记录结束的地方
     * @return the endRow (type int) of this PaginatedArrayList object.
     */

    public int getEndRow() {
        return index * pageSize;
    }

    /**
     * Method repaginate ...
     */
    public void repaginate() {
        if (pageSize < 1) { //防止程序偷懒，list和分页的混合使用
            pageSize = PAGESIZE_DEFAULT;
        }
        if (index < 1) {
            index = 1;//恢复到第一页
        }
        if (totalItem > 0) {
            totalPage = totalItem / pageSize + (totalItem % pageSize > 0 ? 1 : 0);//计算出最大页数
            if(index > totalPage) {//当前页数大于最大页，设置为最大页
                index = totalPage; //最大页
            }
            startRow = (index-1)*pageSize; //计算出页开始行数
        }
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public Boolean getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(Boolean isAsc) {
        this.isAsc = isAsc;
    }
    
    
}
