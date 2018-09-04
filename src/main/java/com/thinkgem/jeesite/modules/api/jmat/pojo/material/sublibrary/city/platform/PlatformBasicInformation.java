package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 平台基础信息(更新)
 * 
 * @author ljc
 * @version 2018-03-15
 */
@ApiModel(value = "平台基础信息(更新)")
public class PlatformBasicInformation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("四级科目ID")
	private String treeID;
	
	@ApiModelProperty("单位")
	private Integer unit;
	
	@ApiModelProperty("功能")
	private String matFunction;
	
	@ApiModelProperty("描述")
	private String matDescription;
	
	@ApiModelProperty("分类:1成品/2非成品")
	private Integer type;

	public String getTreeID() {
		return treeID;
	}

	public void setTreeID(String treeID) {
		this.treeID = treeID;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public String getMatFunction() {
		return matFunction;
	}

	public void setMatFunction(String matFunction) {
		this.matFunction = matFunction;
	}

	public String getMatDescription() {
		return matDescription;
	}

	public void setMatDescription(String matDescription) {
		this.matDescription = matDescription;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
