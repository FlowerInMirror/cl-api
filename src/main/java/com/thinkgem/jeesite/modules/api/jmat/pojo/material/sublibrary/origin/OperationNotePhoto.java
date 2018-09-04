package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 照片操作记录(输出)
 * 
 * @author ljc
 * @version 1.0
 * @createTime 2018-4-11 17:37:01
 */
@ApiModel(value = "照片操作记录(输出)模型")
public class OperationNotePhoto implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("行号")
	private Integer rowNum;
	@ApiModelProperty("主ID（材料照片ID）")
	private String mainID;
	@ApiModelProperty("分类Code（已此做查询条件）")
	private String classCode;
	@ApiModelProperty("一级分类")
	private String treeOneName;
	@ApiModelProperty("二级分类")
	private String treeTwoName;
	@ApiModelProperty("材料名称")
	private String treeThreeName;
	@ApiModelProperty("材料规格")
	private String treeFourName;
	@ApiModelProperty("图片类型")
	private String className;
	@ApiModelProperty("行为内容")
	private String content;
	@ApiModelProperty("操作时间")
	private String operateTime;
	
	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	public String getMainID() {
		return mainID;
	}
	public void setMainID(String mainID) {
		this.mainID = mainID;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	@Override
	public String toString() {
		return "OperationNotePhoto [rowNum=" + rowNum + ", mainID=" + mainID + ", classCode=" + classCode
				+ ", treeOneName=" + treeOneName + ", treeTwoName=" + treeTwoName + ", treeThreeName=" + treeThreeName
				+ ", treeFourName=" + treeFourName + ", className=" + className + ", content=" + content
				+ ", operateTime=" + operateTime + "]";
	}
	
	

}
