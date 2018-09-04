package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 材料操作记录(输出)
 * 
 * @author ljc
 * @version 1.0
 * @createTime 2018-4-11 17:37:01
 */
@ApiModel(value = "材料操作记录(输出)模型")
public class OperationNoteMaterial implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("行号")
	private Integer rowNum;
	@ApiModelProperty("主ID（材料ID：1、2，规格ID：10-14，对比属性ID：15）")
	private String mainID;
	@ApiModelProperty("分类Code（已此做查询条件）")
	private String classCode;	
	@ApiModelProperty("一级分类")
	private String treeOneName = "-";
	@ApiModelProperty("二级分类")
	private String treeTwoName = "-";
	@ApiModelProperty("材料名称")
	private String treeThreeName = "-";
	@ApiModelProperty("材料规格")
	private String treeFourName = "-";
	@ApiModelProperty("操作名称")
	private String className;
	@ApiModelProperty("品牌名称(默认值'-')")
	private String brandName = "-";
	@ApiModelProperty("行为内容")
	private String content;
	@ApiModelProperty("操作时间")
	private String operateTime;
	
	public String getMainID() {
		return mainID;
	}

	public void setMainID(String mainID) {
		this.mainID = mainID;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Override
	public String toString() {
		return "OperationNoteMaterial [rowNum=" + rowNum + ", mainID=" + mainID + ", classCode=" + classCode
				+ ", treeOneName=" + treeOneName + ", treeTwoName=" + treeTwoName + ", treeThreeName=" + treeThreeName
				+ ", treeFourName=" + treeFourName + ", className=" + className + ", brandName=" + brandName
				+ ", content=" + content + ", operateTime=" + operateTime + "]";
	}



}
