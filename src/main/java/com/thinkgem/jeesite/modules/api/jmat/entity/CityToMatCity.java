/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 地区关联Entity
 * @author ljc
 * @version 2018-03-29
 */
public class CityToMatCity extends DataEntity<CityToMatCity> {
	
	private static final long serialVersionUID = 1L;
	private Integer ccId;		// ID
	private Integer ccCity;		// 地区
	private Integer ccCitytomat;		// 材料地区
	private Integer ccStatus;		// 进程：1正常、2删除  （默认1）
	private Date ccCreatetime;		// 创建时间    （默认getdate()）
	private Date ccUpdatetime;		// 更新时间
	
	public CityToMatCity() {
		super();
	}

    public void setCcId(Integer ccId) {
        this.ccId = ccId;
    }

    public void setCcCity(Integer ccCity) {
        this.ccCity = ccCity;
    }

    public void setCcCitytomat(Integer ccCitytomat) {
        this.ccCitytomat = ccCitytomat;
    }

    public void setCcStatus(Integer ccStatus) {
        this.ccStatus = ccStatus;
    }

    public void setCcCreatetime(Date ccCreatetime) {
        this.ccCreatetime = ccCreatetime;
    }

    public void setCcUpdatetime(Date ccUpdatetime) {
        this.ccUpdatetime = ccUpdatetime;
    }

    public Integer getCcId() {
        return ccId;
    }

    public Integer getCcCity() {
        return ccCity;
    }

    public Integer getCcCitytomat() {
        return ccCitytomat;
    }

    public Integer getCcStatus() {
        return ccStatus;
    }

    public Date getCcCreatetime() {
        return ccCreatetime;
    }

    public Date getCcUpdatetime() {
        return ccUpdatetime;
    }
}