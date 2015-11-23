package com.qp.loan.domain;

import java.io.Serializable;
import java.util.Date;

import com.qp.common.base.PaginateBaseDO;

/**
 * @author haiping
 *
 */
public class Ware extends PaginateBaseDO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String img;
	private Integer supplierId;
	private String name;
	private String sku;
	private String unit;
	private Integer minOrderNum;
	private Integer price;
	private Integer presellPrice;
	private Short state;
	private Date created;
	private Date modified;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getMinOrderNum() {
		return minOrderNum;
	}
	public void setMinOrderNum(Integer minOrderNum) {
		this.minOrderNum = minOrderNum;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getPresellPrice() {
		return presellPrice;
	}
	public void setPresellPrice(Integer presellPrice) {
		this.presellPrice = presellPrice;
	}
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	
}
