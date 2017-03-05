package com.qjp.entity;


/**
 * 销售机会
 * @author Administrator
 *
 */
public class ProductEntity extends CRMBaseEntity{
	private String productName; //产品名称
	private String price; //标准价格
	private String saleUnit; //销售单位
	private String productDesc; //产品描述
	private Integer status;//启用状态
	private String imgPath; //产品图片
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSaleUnit() {
		return saleUnit;
	}
	public void setSaleUnit(String saleUnit) {
		this.saleUnit = saleUnit;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
