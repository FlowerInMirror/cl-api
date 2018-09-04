/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 价格变更记录Entity
 * @author ljc
 * @version 2018-05-09
 */
public class PriceChangeHistory extends DataEntity<PriceChangeHistory> {
	
	private static final long serialVersionUID = 1L;
	private Integer pchId;		// ID
	private String pchMatid;		// 材料ID
	private Integer pchUserid;		// 材料商ID
	private Double pchOldprice;		// 原价格
	private Double pchNewprice;		// 现价格
	private Integer pchClass;		// 分类：1成本价，2报价，3安装价，4交易价
	private Integer pchStatus;		// 审核状态：1待审核，2，通过，3打回
	private Integer pchType;		// 变更形式：1APP配送，2临时配送，3地方，4集团
	private Date pchTime;		// 时间
	private Date pchChangetime;		// 变更日期
	private String pchRemark;		// 变更原因
	private String pchCheckremark;		// 审核备注
	
	public PriceChangeHistory() {
		super();
	}

	public PriceChangeHistory(String id){
		super(id);
	}

	@NotNull(message="ID不能为空")
	public Integer getPchId() {
		return pchId;
	}

	public void setPchId(Integer pchId) {
		this.pchId = pchId;
	}
	
	public String getPchMatid() {
		return pchMatid;
	}

	public void setPchMatid(String pchMatid) {
		this.pchMatid = pchMatid;
	}
	
	public Integer getPchUserid() {
		return pchUserid;
	}

	public void setPchUserid(Integer pchUserid) {
		this.pchUserid = pchUserid;
	}
	
	public Double getPchOldprice() {
		return pchOldprice;
	}

	public void setPchOldprice(Double pchOldprice) {
		this.pchOldprice = pchOldprice;
	}
	
	public Double getPchNewprice() {
		return pchNewprice;
	}

	public void setPchNewprice(Double pchNewprice) {
		this.pchNewprice = pchNewprice;
	}
	
	public Integer getPchClass() {
		return pchClass;
	}

	public void setPchClass(Integer pchClass) {
		this.pchClass = pchClass;
	}
	
	public Integer getPchStatus() {
		return pchStatus;
	}

	public void setPchStatus(Integer pchStatus) {
		this.pchStatus = pchStatus;
	}
	
	public Integer getPchType() {
		return pchType;
	}

	public void setPchType(Integer pchType) {
		this.pchType = pchType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPchTime() {
		return pchTime;
	}

	public void setPchTime(Date pchTime) {
		this.pchTime = pchTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPchChangetime() {
		return pchChangetime;
	}

	public void setPchChangetime(Date pchChangetime) {
		this.pchChangetime = pchChangetime;
	}
	
	@Length(min=0, max=2000, message="变更原因长度必须介于 0 和 2000 之间")
	public String getPchRemark() {
		return pchRemark;
	}

	public void setPchRemark(String pchRemark) {
		this.pchRemark = pchRemark;
	}
	
	@Length(min=0, max=2000, message="审核备注长度必须介于 0 和 2000 之间")
	public String getPchCheckremark() {
		return pchCheckremark;
	}

	public void setPchCheckremark(String pchCheckremark) {
		this.pchCheckremark = pchCheckremark;
	}
	
}