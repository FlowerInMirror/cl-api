/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 材料表关联性汇总表Entity
 * @author ljc
 * @version 2018-06-16
 */
public class TabkeRelation extends DataEntity<TabkeRelation> {
	
	private static final long serialVersionUID = 1L;
	private String trId;		// ID
	private Integer trType;		// 类型：1平台展示图
	private String trOneid;		// 表AID(1科目树ID)
	private Integer trOneidint;		// 表AID INT类型ID
	private String trTwoid;		// 表BID(1分类表ID V_TypeInfo)
	private Integer trTwoidint;		// 表BID INT类型ID
	private Integer trSort;		// 排序
	private Integer trStatus;		// 进程：0正常、1删除  （默认0）
	private Date trTime;		// 时间（可用作排序）    （默认getdate()）
	
	public TabkeRelation() {
		super();
	}

	public TabkeRelation(String id){
		super(id);
	}

	public String getTrId() {
		return trId;
	}

	public void setTrId(String trId) {
		this.trId = trId;
	}
	
	public Integer getTrType() {
		return trType;
	}

	public void setTrType(Integer trType) {
		this.trType = trType;
	}
	
	public String getTrOneid() {
		return trOneid;
	}

	public void setTrOneid(String trOneid) {
		this.trOneid = trOneid;
	}
	
	public Integer getTrOneidint() {
		return trOneidint;
	}

	public void setTrOneidint(Integer trOneidint) {
		this.trOneidint = trOneidint;
	}
	
	public String getTrTwoid() {
		return trTwoid;
	}

	public void setTrTwoid(String trTwoid) {
		this.trTwoid = trTwoid;
	}
	
	public Integer getTrTwoidint() {
		return trTwoidint;
	}

	public void setTrTwoidint(Integer trTwoidint) {
		this.trTwoidint = trTwoidint;
	}
	
	public Integer getTrSort() {
		return trSort;
	}

	public void setTrSort(Integer trSort) {
		this.trSort = trSort;
	}
	
	public Integer getTrStatus() {
		return trStatus;
	}

	public void setTrStatus(Integer trStatus) {
		this.trStatus = trStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTrTime() {
		return trTime;
	}

	public void setTrTime(Date trTime) {
		this.trTime = trTime;
	}
	
}