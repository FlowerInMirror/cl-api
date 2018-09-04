package com.thinkgem.jeesite.modules.api.jmat.pojo.commons;

import java.io.Serializable;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 科目树(包装类)
 * 
 * @author ljc
 * @version 2018-03-31
 * @action IO-数据载体
 */
@ApiModel(value = "科目树包装类")
public class TreeVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("ID")
	private String treeId; // ID

	@ApiModelProperty("编号")
	private String treeCode; // 编号

	@ApiModelProperty("名称")
	private String treeName; // 名称

	@ApiModelProperty("父级ID")
	private String treeParentid; // 父级ID

	@ApiModelProperty("级别：从1开始")
	private Integer treeLevel; // 级别：从1开始

	@ApiModelProperty("完善状态：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）")
	private Integer treePerfectstate=-1; // 完善状态：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）

	@ApiModelProperty("进程：0正常、1删除  （默认0）")
	private Integer treeJincheng; // 进程：0正常、1删除 （默认0）

	@ApiModelProperty("创建时间    （默认getdate()）")
	private Date treeCreatetime; // 创建时间 （默认getdate()）

	@ApiModelProperty("更新时间")
	private Date treeUpdatetime; // 更新时间

	@ApiModelProperty("排序（根据使用次数--项目）（针对第四级）")
	private Integer treeOrder; // 排序（根据使用次数--项目）（针对第四级）

//  字段暂未启用
//	@ApiModelProperty("类型：1成品安装，2非成品安装 （针对规格）默认0） ")
//	private Integer treeProductType;
//
//	public Integer getTreeProductType() {
//		return treeProductType;
//	}
//
//	public void setTreeProductType(Integer treeProductType) {
//		this.treeProductType = treeProductType;
//	}


	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public String getTreeParentid() {
		return treeParentid;
	}

	public void setTreeParentid(String treeParentid) {
		this.treeParentid = treeParentid;
	}

	public Integer getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}

	public Integer getTreePerfectstate() {
		return treePerfectstate;
	}

	public void setTreePerfectstate(Integer treePerfectstate) {
		this.treePerfectstate = treePerfectstate;
	}

	public Integer getTreeJincheng() {
		return treeJincheng;
	}

	public void setTreeJincheng(Integer treeJincheng) {
		this.treeJincheng = treeJincheng;
	}

	public Date getTreeCreatetime() {
		return treeCreatetime;
	}

	public void setTreeCreatetime(Date treeCreatetime) {
		this.treeCreatetime = treeCreatetime;
	}

	public Date getTreeUpdatetime() {
		return treeUpdatetime;
	}

	public void setTreeUpdatetime(Date treeUpdatetime) {
		this.treeUpdatetime = treeUpdatetime;
	}

	public Integer getTreeOrder() {
		return treeOrder;
	}

	public void setTreeOrder(Integer treeOrder) {
		this.treeOrder = treeOrder;
	}

}
