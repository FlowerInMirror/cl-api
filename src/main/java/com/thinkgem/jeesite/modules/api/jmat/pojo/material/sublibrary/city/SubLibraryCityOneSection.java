package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 子库地区一段
 * 
 * @author  ljc
 * @version 2018-03-15
 * @action  响应数据载体
 */
@ApiModel(value = "子库地区一段")
public class SubLibraryCityOneSection implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("一级科目名")
	private String treeOneName;// 一级科目名
	@ApiModelProperty("二级科目名")
	private String treeTwoName;// 二级科目名
	@ApiModelProperty("材料名称(三级科目名)")
	private String matName;// 材料名称(三级科目名)
	@ApiModelProperty("材料规格(四级科目名)")
	private String matSpec;// 材料规格(四级科目名)
	@ApiModelProperty("一级科目ID")
	private String treeOneID;// 一级科目ID
	@ApiModelProperty("二级科目ID")
	private String treeTwoID;// 二级科目ID
	@ApiModelProperty("三级科目ID")
	private String treeThreeID;// 三级科目ID
	@ApiModelProperty("四级科目ID")
	private String treeFourID;// 四级科目ID
	@ApiModelProperty("排序（根据使用次数--项目）（针对第四级）")
	private Integer treeOrder;// 排序（根据使用次数--项目）（针对第四级）

	public Integer getTreeOrder() {
		return treeOrder;
	}

	public void setTreeOrder(Integer treeOrder) {
		this.treeOrder = treeOrder;
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

	public String getMatName() {
		return matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}

	public String getMatSpec() {
		return matSpec;
	}

	public void setMatSpec(String matSpec) {
		this.matSpec = matSpec;
	}

	public String getTreeOneID() {
		return treeOneID;
	}

	public void setTreeOneID(String treeOneID) {
		this.treeOneID = treeOneID;
	}

	public String getTreeTwoID() {
		return treeTwoID;
	}

	public void setTreeTwoID(String treeTwoID) {
		this.treeTwoID = treeTwoID;
	}

	public String getTreeThreeID() {
		return treeThreeID;
	}

	public void setTreeThreeID(String treeThreeID) {
		this.treeThreeID = treeThreeID;
	}

	public String getTreeFourID() {
		return treeFourID;
	}

	public void setTreeFourID(String treeFourID) {
		this.treeFourID = treeFourID;
	}

}
