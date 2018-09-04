/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 专项材料关联表Entity
 * @author ljc
 * @version 2018-08-08
 */
public class SpecialMatRelevance implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer smrID;		// ID 标识
	private String smrSPID;		// 专项产品ID
	private String smrMatID;		// 材料ID(专项产品1对1)
	private Integer smrStatus;		// 进程：0正常、1删除  （默认0）
	private Date smrCreateTime;		// 创建时间    （默认getdate()）
	private Date smrUpdateTime;		// 更新时间

	public SpecialMatRelevance() {
		super();
	}

	// 有参:检索One
    public SpecialMatRelevance(String smrSPID) {
        this.smrSPID = smrSPID;
    }

    public Integer getSmrID() {
		return smrID;
	}

	public void setSmrID(Integer smrID) {
		this.smrID = smrID;
	}
	
	public String getSmrSPID() {
		return smrSPID;
	}

	public SpecialMatRelevance setSmrSPID(String smrSPID) {
		this.smrSPID = smrSPID;
		return this;
	}
	
	public String getSmrMatID() {
		return smrMatID;
	}

	public void setSmrMatID(String smrMatID) {
		this.smrMatID = smrMatID;
	}
	
	public Integer getSmrStatus() {
		return smrStatus;
	}

	public void setSmrStatus(Integer smrStatus) {
		this.smrStatus = smrStatus;
	}
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSmrCreateTime() {
		return smrCreateTime;
	}

	public void setSmrCreateTime(Date smrCreateTime) {
		this.smrCreateTime = smrCreateTime;
	}
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSmrUpdateTime() {
		return smrUpdateTime;
	}

	public void setSmrUpdateTime(Date smrUpdateTime) {
		this.smrUpdateTime = smrUpdateTime;
	}
	
}