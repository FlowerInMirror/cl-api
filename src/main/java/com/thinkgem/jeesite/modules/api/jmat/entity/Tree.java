/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 科目树Entity
 * @author ljc
 * @version 2018-03-15
 */
public class Tree extends DataEntity<Tree> {
	
	private static final long serialVersionUID = 1L;
	private String treeId;		// ID
	private String treeCode;		// 编号
	private String treeName;		// 名称
	private String treeParentid;		// 父级ID
	private Integer treeLevel;		// 级别：从1开始
	private Integer treePerfectstate;		// 完善状态：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）
	private Integer treeJincheng;		// 进程：0正常、1删除  （默认0）
	private Date treeCreatetime;		// 创建时间    （默认getdate()）
	private Date treeUpdatetime;		// 更新时间
	private Integer treeOrder;		// 排序（根据使用次数--项目）（针对第四级）
	
	//---新增字段---
//  字段暂未启用
//	private Integer treeProductType;
	
//	public Integer getTreeProductType() {
//		return treeProductType;
//	}
	
//	public void setTreeProductType(Integer treeProductType) {
//		this.treeProductType = treeProductType;
//	}

	public Tree() {
		super();
	}

	public Tree(String id){
		super(id);
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}
	
	@Length(min=0, max=10, message="编号长度必须介于 0 和 10 之间")
	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}
	
	@Length(min=0, max=50, message="名称长度必须介于 0 和 50 之间")
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTreeCreatetime() {
		return treeCreatetime;
	}

	public void setTreeCreatetime(Date treeCreatetime) {
		this.treeCreatetime = treeCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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