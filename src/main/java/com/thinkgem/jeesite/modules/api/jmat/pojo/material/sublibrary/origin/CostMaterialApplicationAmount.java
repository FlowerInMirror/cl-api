package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 材料使用量模型(输出)
 * 
 * @author     ljc
 * @version    1.0
 * @createTime 2018-4-14 18:30:54
 */
@ApiModel(value = "材料使用量模型对象(输出)")
public class CostMaterialApplicationAmount implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("用量排名")
	private Integer TopNum;
	@ApiModelProperty("材料ID")
	private String matID;
	@ApiModelProperty("一级分类")
	private String treeOneName;
	@ApiModelProperty("二级分类")
	private String treeTwoName;
	@ApiModelProperty("材料名称")
	private String treeThreeName;
	@ApiModelProperty("材料规格")
	private String treeFourName;
	@ApiModelProperty("单位名称")
	private String unitName;
	@ApiModelProperty("编码")
	private String matCode;
	@ApiModelProperty("品牌名称")
	private String brandName;
	@ApiModelProperty("型号")
	private String brandType;
	@ApiModelProperty("成本价")
	private Double costPrice;
	@ApiModelProperty("报价")
	private Double quotesPrice;
	@ApiModelProperty("安装价")
	private Double installPrice;
	@ApiModelProperty("用量平均价")
	private Double matAvgPrice;
	@ApiModelProperty("全国平均价")
	private Double nationwideMatAvgPrice;
	@ApiModelProperty("材料档次")
	private String matLevel;
	@ApiModelProperty("总单量")
	private Double totalSingleQuantity;
	
	public Integer getTopNum() {
		return TopNum;
	}
	public void setTopNum(Integer topNum) {
		TopNum = topNum;
	}
	public String getMatID() {
		return matID;
	}
	public void setMatID(String matID) {
		this.matID = matID;
	}
	public String getTreeOneName() {
		return treeOneName;
	}
	public void setTreeOneName(String treeOneName) {
		this.treeOneName = treeOneName;
	}
	public String getTreeTwoName() {
		return treeTwoName;
	}
	public void setTreeTwoName(String treeTwoName) {
		this.treeTwoName = treeTwoName;
	}
	public String getTreeThreeName() {
		return treeThreeName;
	}
	public void setTreeThreeName(String treeThreeName) {
		this.treeThreeName = treeThreeName;
	}
	public String getTreeFourName() {
		return treeFourName;
	}
	public void setTreeFourName(String treeFourName) {
		this.treeFourName = treeFourName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getMatCode() {
		return matCode;
	}
	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandType() {
		return brandType;
	}
	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Double getQuotesPrice() {
		return quotesPrice;
	}
	public void setQuotesPrice(Double quotesPrice) {
		this.quotesPrice = quotesPrice;
	}
	public Double getInstallPrice() {
		return installPrice;
	}
	public void setInstallPrice(Double installPrice) {
		this.installPrice = installPrice;
	}
	public Double getMatAvgPrice() {
		return matAvgPrice;
	}
	public void setMatAvgPrice(Double matAvgPrice) {
		this.matAvgPrice = matAvgPrice;
	}
	public Double getNationwideMatAvgPrice() {
		return nationwideMatAvgPrice;
	}
	public void setNationwideMatAvgPrice(Double nationwideMatAvgPrice) {
		this.nationwideMatAvgPrice = nationwideMatAvgPrice;
	}
	public String getMatLevel() {
		return matLevel;
	}
	public void setMatLevel(String matLevel) {
		this.matLevel = matLevel;
	}
	public Double getTotalSingleQuantity() {
		return totalSingleQuantity;
	}
	public void setTotalSingleQuantity(Double totalSingleQuantity) {
		this.totalSingleQuantity = totalSingleQuantity;
	}

}
