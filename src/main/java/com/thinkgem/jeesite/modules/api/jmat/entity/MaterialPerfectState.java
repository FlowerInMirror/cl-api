/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 材料子库完善状态(用于保存地区信息)Entity
 * @author ljc
 * @version 2018-03-29
 */
public class MaterialPerfectState extends DataEntity<MaterialPerfectState> {
	
	private static final long serialVersionUID = 1L;
	private Integer mpsId;		// ID
	private String mpsTreefour;		// 四级ID（规格）
	private Integer mpsCityid;		// 地区
	private Integer mpsBasestate;		// 基本信息：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）
	private Integer mpsPhotostate;		// 图片管理：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）
	private Integer mpsPricestate;		// 材料商价格：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）
	private Integer mpsShopingstate;		// 是否已上架：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）
	private Integer mpsJincheng;		// 进程：0正常、1删除  （默认0）
	private Date mpsCreatetime;		// 创建时间    （默认getdate()）
	private Date mpsUpdatetime;		// 更新时间
	
	public MaterialPerfectState() {
		super();
	}

	public MaterialPerfectState(String id){
		super(id);
	}

	@NotNull(message="ID不能为空")
	public Integer getMpsId() {
		return mpsId;
	}

	public void setMpsId(Integer mpsId) {
		this.mpsId = mpsId;
	}
	
	public String getMpsTreefour() {
		return mpsTreefour;
	}

	public void setMpsTreefour(String mpsTreefour) {
		this.mpsTreefour = mpsTreefour;
	}
	
	public Integer getMpsCityid() {
		return mpsCityid;
	}

	public void setMpsCityid(Integer mpsCityid) {
		this.mpsCityid = mpsCityid;
	}
	
	public Integer getMpsBasestate() {
		return mpsBasestate;
	}

	public void setMpsBasestate(Integer mpsBasestate) {
		this.mpsBasestate = mpsBasestate;
	}
	
	public Integer getMpsPhotostate() {
		return mpsPhotostate;
	}

	public void setMpsPhotostate(Integer mpsPhotostate) {
		this.mpsPhotostate = mpsPhotostate;
	}
	
	public Integer getMpsPricestate() {
		return mpsPricestate;
	}

	public void setMpsPricestate(Integer mpsPricestate) {
		this.mpsPricestate = mpsPricestate;
	}
	
	public Integer getMpsShopingstate() {
		return mpsShopingstate;
	}

	public void setMpsShopingstate(Integer mpsShopingstate) {
		this.mpsShopingstate = mpsShopingstate;
	}
	
	public Integer getMpsJincheng() {
		return mpsJincheng;
	}

	public void setMpsJincheng(Integer mpsJincheng) {
		this.mpsJincheng = mpsJincheng;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMpsCreatetime() {
		return mpsCreatetime;
	}

	public void setMpsCreatetime(Date mpsCreatetime) {
		this.mpsCreatetime = mpsCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMpsUpdatetime() {
		return mpsUpdatetime;
	}

	public void setMpsUpdatetime(Date mpsUpdatetime) {
		this.mpsUpdatetime = mpsUpdatetime;
	}
	
}