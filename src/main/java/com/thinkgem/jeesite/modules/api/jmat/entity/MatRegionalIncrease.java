/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 地区材料涨幅关联表(上月材料地区排行数据)Entity
 * @author ljc
 * @version 2018-04-04
 */
public class MatRegionalIncrease extends DataEntity<MatRegionalIncrease> {
	
	private static final long serialVersionUID = 1L;
	private Integer mriId;		// ID(主键)
	private Integer mriRanking;		// 整体排名
	private Integer mriCityid;		// 地区ID
	private Double mriTotalprice;		// 材料总价
	private Date mriCreatetime;		// 创建时间    （默认getdate()）
	
	public MatRegionalIncrease() {
		super();
	}

	public MatRegionalIncrease(String id){
		super(id);
	}

	public Integer getMriId() {
		return mriId;
	}

	public void setMriId(Integer mriId) {
		this.mriId = mriId;
	}
	
	public Integer getMriRanking() {
		return mriRanking;
	}

	public void setMriRanking(Integer mriRanking) {
		this.mriRanking = mriRanking;
	}
	
	public Integer getMriCityid() {
		return mriCityid;
	}

	public void setMriCityid(Integer mriCityid) {
		this.mriCityid = mriCityid;
	}
	
	public Double getMriTotalprice() {
		return mriTotalprice;
	}

	public void setMriTotalprice(Double mriTotalprice) {
		this.mriTotalprice = mriTotalprice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMriCreatetime() {
		return mriCreatetime;
	}

	public void setMriCreatetime(Date mriCreatetime) {
		this.mriCreatetime = mriCreatetime;
	}
	
}