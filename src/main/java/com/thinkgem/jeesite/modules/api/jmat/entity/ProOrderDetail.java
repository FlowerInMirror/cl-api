/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 配送单明细Entity
 * @author ljc
 * @version 2018-04-14
 */
public class ProOrderDetail extends DataEntity<ProOrderDetail> {
	
	private static final long serialVersionUID = 1L;
	private String podId;		// 配送单明细  主键ID
	private String podPoid;		// 配送单ID
	private String podPmiid;		// 项目材料明细表ID
	private Integer podType;		// 材料类型：1常用材料、2不常用材料  （针对不常用材料）
	private String podMatid;		// 对应材料ID  （针对不常用材料） 注：可能是不常用材料ID，也可能是常用材料ID
	private Double podMatprice;		// 单价
	private Double podOriginalcount;		// 原始下单量
	private Double podAlreadycount;		// 实际下单量
	private Integer podJincheng;		// 进程：0正常、1删除  （默认0）
	private Date podCreatetime;		// 创建时间    （默认getdate()）
	private Date podUpdatetime;		// 更新时间
	
	public ProOrderDetail() {
		super();
	}

	public ProOrderDetail(String id){
		super(id);
	}

	public String getPodId() {
		return podId;
	}

	public void setPodId(String podId) {
		this.podId = podId;
	}
	
	public String getPodPoid() {
		return podPoid;
	}

	public void setPodPoid(String podPoid) {
		this.podPoid = podPoid;
	}
	
	public String getPodPmiid() {
		return podPmiid;
	}

	public void setPodPmiid(String podPmiid) {
		this.podPmiid = podPmiid;
	}
	
	public Integer getPodType() {
		return podType;
	}

	public void setPodType(Integer podType) {
		this.podType = podType;
	}
	
	public String getPodMatid() {
		return podMatid;
	}

	public void setPodMatid(String podMatid) {
		this.podMatid = podMatid;
	}
	
	public Double getPodMatprice() {
		return podMatprice;
	}

	public void setPodMatprice(Double podMatprice) {
		this.podMatprice = podMatprice;
	}
	
	public Double getPodOriginalcount() {
		return podOriginalcount;
	}

	public void setPodOriginalcount(Double podOriginalcount) {
		this.podOriginalcount = podOriginalcount;
	}
	
	public Double getPodAlreadycount() {
		return podAlreadycount;
	}

	public void setPodAlreadycount(Double podAlreadycount) {
		this.podAlreadycount = podAlreadycount;
	}
	
	public Integer getPodJincheng() {
		return podJincheng;
	}

	public void setPodJincheng(Integer podJincheng) {
		this.podJincheng = podJincheng;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPodCreatetime() {
		return podCreatetime;
	}

	public void setPodCreatetime(Date podCreatetime) {
		this.podCreatetime = podCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPodUpdatetime() {
		return podUpdatetime;
	}

	public void setPodUpdatetime(Date podUpdatetime) {
		this.podUpdatetime = podUpdatetime;
	}
	
}