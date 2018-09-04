/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 专项套餐表Entity
 * @author ljc
 * @version 2018-08-08
 */
public class SpecialSetMeal implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	private String ssmID;		// ID
	private String ssmSPID;		// 专项产品ID
	private String ssmMealName;		// 套餐名称
	private String ssmMealDepict;		// 套餐描述
	private Integer ssmMealType;		// 套餐类型备用字段（默认0）
	private Double ssmMealPrice;		// 套餐价格
	private String ssmChargeUnit;		// 套餐计价单位
	private String ssmMealPhotoURL;		// 套餐图片地址
	private Integer ssmPushType;		// 是否主推：0不主推、1主推  （默认0）
	private Integer ssmStatus;		// 进程：0正常、1删除、2下架  （默认0）
	private Date ssmCreateTime;		// 创建时间    （默认getdate()）
	private Date ssmUpdateTtime;		// 更新时间
	
	public SpecialSetMeal() {
		super();
	}

    public SpecialSetMeal(String ssmSPID) {
        this.ssmSPID = ssmSPID;
    }



    public String getSsmID() {
		return ssmID;
	}

	public void setSsmID(String ssmID) {
		this.ssmID = ssmID;
	}
	
	public String getSsmSPID() {
		return ssmSPID;
	}

	public void setSsmSPID(String ssmSPID) {
		this.ssmSPID = ssmSPID;
	}
	
	public String getSsmMealName() {
		return ssmMealName;
	}

	public void setSsmMealName(String ssmMealName) {
		this.ssmMealName = ssmMealName;
	}
	
	public String getSsmMealDepict() {
		return ssmMealDepict;
	}

	public void setSsmMealDepict(String ssmMealDepict) {
		this.ssmMealDepict = ssmMealDepict;
	}
	
	public Integer getSsmMealType() {
		return ssmMealType;
	}

	public void setSsmMealType(Integer ssmMealType) {
		this.ssmMealType = ssmMealType;
	}
	
	public Double getSsmMealPrice() {
		return ssmMealPrice;
	}

	public void setSsmMealPrice(Double ssmMealPrice) {
		this.ssmMealPrice = ssmMealPrice;
	}
	
	public String getSsmChargeUnit() {
		return ssmChargeUnit;
	}

	public void setSsmChargeUnit(String ssmChargeUnit) {
		this.ssmChargeUnit = ssmChargeUnit;
	}
	
	public String getSsmMealPhotoURL() {
		return ssmMealPhotoURL;
	}

	public void setSsmMealPhotoURL(String ssmMealPhotoURL) {
		this.ssmMealPhotoURL = ssmMealPhotoURL;
	}
	
	public Integer getSsmPushType() {
		return ssmPushType;
	}

	public void setSsmPushType(Integer ssmPushType) {
		this.ssmPushType = ssmPushType;
	}
	
	public Integer getSsmStatus() {
		return ssmStatus;
	}

	public void setSsmStatus(Integer ssmStatus) {
		this.ssmStatus = ssmStatus;
	}
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSsmCreateTime() {
		return ssmCreateTime;
	}

	public void setSsmCreateTime(Date ssmCreateTime) {
		this.ssmCreateTime = ssmCreateTime;
	}
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSsmUpdateTtime() {
		return ssmUpdateTtime;
	}

	public void setSsmUpdateTtime(Date ssmUpdateTtime) {
		this.ssmUpdateTtime = ssmUpdateTtime;
	}


}