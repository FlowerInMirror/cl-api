package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city;

import java.io.Serializable;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.TreeVo;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 地区新增标准(添加)
 * 
 * @author ljc
 * @version 2018-03-31
 * @action 入参适配数据载体
 */
@ApiModel(value = "地区新增标准(添加模型)")
public class CityNewlyIncreased implements Serializable {

	private static final long serialVersionUID = 1L;

	// ---科目树---
	@ApiModelProperty("一级分类")
	private TreeVo treeOne;// 一级分类
	@ApiModelProperty("二级分类")
	private TreeVo treeTwo;// 二级分类
	@ApiModelProperty("材料名称")
	private TreeVo treeThree;// 材料名称
	@ApiModelProperty("材料规格")
	private TreeVo treeFour;// 材料规格

	// ---基础---
	@ApiModelProperty("单位ID")
	private Integer unitID;// 单位ID
	@ApiModelProperty("单位名称")
	private String unitName;// 单位名称

	@ApiModelProperty("材料分类(1.成品类;2.非成品类)")
	private Integer classify;// 材料分类(1.成品类;2.非成品类)

	@ApiModelProperty("档次总记")
	private Integer levelCount;// 档次

	// ---地区---
	@ApiModelProperty("地区Id,字符串")
	private String cityIds;

	public TreeVo getTreeOne() {
		return treeOne;
	}

	public void setTreeOne(TreeVo treeOne) {
		this.treeOne = treeOne;
	}

	public TreeVo getTreeTwo() {
		return treeTwo;
	}

	public void setTreeTwo(TreeVo treeTwo) {
		this.treeTwo = treeTwo;
	}

	public TreeVo getTreeThree() {
		return treeThree;
	}

	public void setTreeThree(TreeVo treeThree) {
		this.treeThree = treeThree;
	}

	public TreeVo getTreeFour() {
		return treeFour;
	}

	public void setTreeFour(TreeVo treeFour) {
		this.treeFour = treeFour;
	}

	public Integer getUnitID() {
		return unitID;
	}

	public void setUnitID(Integer unitID) {
		this.unitID = unitID;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Integer getClassify() {
		return classify;
	}

	public void setClassify(Integer classify) {
		this.classify = classify;
	}

	public Integer getLevelCount() {
		return levelCount;
	}

	public void setLevelCount(Integer levelCount) {
		this.levelCount = levelCount;
	}

	public String getCityIds() {
		return cityIds;
	}

	public void setCityIds(String cityIds) {
		this.cityIds = cityIds;
	}

}
