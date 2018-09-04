/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.entity;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 专项图片表Entity
 * @author ljc
 * @version 2018-08-17
 */
public class SpecialPicture implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String sdpId;		// ID
	private String spdSpid;		// 专项产品ID
	private Integer spdType;		// 图片类型:1产品主图（默认0）
	private String spdUrl;		// 图片路径
	private Integer spdStatus;		// 进程：0正常、1删除  （默认0）
	private Date spdCreatetime;		// 创建时间    （默认getdate()）
	private Date spdUpdatetime;		// 更新时间
	
	public SpecialPicture() {
		super();
	}

	public String getSdpId() {
		return sdpId;
	}

	public void setSdpId(String sdpId) {
		this.sdpId = sdpId;
	}
	
	public String getSpdSpid() {
		return spdSpid;
	}

	public void setSpdSpid(String spdSpid) {
		this.spdSpid = spdSpid;
	}
	
	public Integer getSpdType() {
		return spdType;
	}

	public void setSpdType(Integer spdType) {
		this.spdType = spdType;
	}
	
	@Length(min=0, max=256, message="图片路径长度必须介于 0 和 256 之间")
	public String getSpdUrl() {
		return spdUrl;
	}

	public void setSpdUrl(String spdUrl) {
		this.spdUrl = spdUrl;
	}
	
	public Integer getSpdStatus() {
		return spdStatus;
	}

	public void setSpdStatus(Integer spdStatus) {
		this.spdStatus = spdStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSpdCreatetime() {
		return spdCreatetime;
	}

	public void setSpdCreatetime(Date spdCreatetime) {
		this.spdCreatetime = spdCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSpdUpdatetime() {
		return spdUpdatetime;
	}

	public void setSpdUpdatetime(Date spdUpdatetime) {
		this.spdUpdatetime = spdUpdatetime;
	}
	
}