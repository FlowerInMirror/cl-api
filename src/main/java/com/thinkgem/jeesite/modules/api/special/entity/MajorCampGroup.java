/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.entity;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 主营属性组合表Entity
 * @author ljc
 * @version 2018-08-17
 */
public class MajorCampGroup implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer mcgId;		// ID 标识
	private String mcgSpid;		// 专项产品ID
	private String mcgCode;		// 编码(主营属性类别项组合)
	private Double mcgPrice;		// 价格
	private Integer mcgOrder;		// 排序 (未启用)
	private Integer mcgPushtype;		// 是否主推：0否、1是  （默认0）
	private Integer mcgStatus;		// 进程：0正常、1删除  （默认0）
	private Date mcgCreatetime;		// 创建时间    （默认getdate()）
	private Date mcgUpdatetime;		// 更新时间
	
	public MajorCampGroup() {
		super();
	}


	public Integer getMcgId() {
		return mcgId;
	}

	public void setMcgId(Integer mcgId) {
		this.mcgId = mcgId;
	}
	
	public String getMcgSpid() {
		return mcgSpid;
	}

	public void setMcgSpid(String mcgSpid) {
		this.mcgSpid = mcgSpid;
	}
	
	public String getMcgCode() {
		return mcgCode;
	}

	public void setMcgCode(String mcgCode) {
		this.mcgCode = mcgCode;
	}
	
	public Double getMcgPrice() {
		return mcgPrice;
	}

	public void setMcgPrice(Double mcgPrice) {
		this.mcgPrice = mcgPrice;
	}
	
	public Integer getMcgOrder() {
		return mcgOrder;
	}

	public void setMcgOrder(Integer mcgOrder) {
		this.mcgOrder = mcgOrder;
	}
	
	public Integer getMcgPushtype() {
		return mcgPushtype;
	}

	public void setMcgPushtype(Integer mcgPushtype) {
		this.mcgPushtype = mcgPushtype;
	}
	
	public Integer getMcgStatus() {
		return mcgStatus;
	}

    @JsonIgnore
	public void setMcgStatus(Integer mcgStatus) {
		this.mcgStatus = mcgStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMcgCreatetime() {
		return mcgCreatetime;
	}

	public void setMcgCreatetime(Date mcgCreatetime) {
		this.mcgCreatetime = mcgCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMcgUpdatetime() {
		return mcgUpdatetime;
	}

	public void setMcgUpdatetime(Date mcgUpdatetime) {
		this.mcgUpdatetime = mcgUpdatetime;
	}
	
}