package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 地区新增相关回显数据(输出)
 * 
 * @author  ljc
 * @version 2018-03-29
 * @action  响应数据载体
 */
@ApiModel(value = "地区新增相关回显(科目树 输出映射)")
public class CityNewlyIncreasedCorrelationEcho implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("ID(添加所需字段)")
	private String treeID;

	@ApiModelProperty("名称(回显所需数据)")
	private String treeName;

	@ApiModelProperty("级别")
	private Integer treeLevel;

	@ApiModelProperty("编码(添加所需字段)")
	private String treeCode;

	@ApiModelProperty("父级ID(添加所需字段)")
	private String treeParentID;

	public String getTreeID() {
		return treeID;
	}

	public void setTreeID(String treeID) {
		this.treeID = treeID;
	}

	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public Integer getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}

	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	public String getTreeParentID() {
		return treeParentID;
	}

	public void setTreeParentID(String treeParentID) {
		this.treeParentID = treeParentID;
	}

}
