/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 材料母库完善状态(保存档次信息)Entity
 * @author ljc
 * @version 2018-03-29
 */
public class TreePerfectState extends DataEntity<TreePerfectState> {
	
	private static final long serialVersionUID = 1L;
	private String tpsTreefour;		// 四级ID（规格）
	private Integer tpsBasestate;		// 基本信息：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）
	private Integer tpsStandardstate;		// 材料标准：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）
	private Integer tpsLinkstate;		// 关联产品：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）
	private Integer tpsCraftstate;		// 材料工艺：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）
	private Integer tpsPhotostate;		// 图片管理：-1未完善，1A档，2B档，4C档（位运算处理|） （默认-1）
	private String tpsExteriorname;		// 外观
	private String tpsExteriorsc;		// 外观-特殊要求
	private String tpsExteriordm;		// 外观-检测方法
	private Integer tpsJincheng;		// 进程：0正常、1删除  （默认0）
	private Date tpsCreatetime;		// 创建时间    （默认getdate()）
	private Date tpsUpdatetime;		// 更新时间
	
	public TreePerfectState() {
		super();
	}

	public TreePerfectState(String id){
		super(id);
	}

	public String getTpsTreefour() {
		return tpsTreefour;
	}

	public void setTpsTreefour(String tpsTreefour) {
		this.tpsTreefour = tpsTreefour;
	}
	
	public Integer getTpsBasestate() {
		return tpsBasestate;
	}

	public void setTpsBasestate(Integer tpsBasestate) {
		this.tpsBasestate = tpsBasestate;
	}
	
	public Integer getTpsStandardstate() {
		return tpsStandardstate;
	}

	public void setTpsStandardstate(Integer tpsStandardstate) {
		this.tpsStandardstate = tpsStandardstate;
	}
	
	public Integer getTpsLinkstate() {
		return tpsLinkstate;
	}

	public void setTpsLinkstate(Integer tpsLinkstate) {
		this.tpsLinkstate = tpsLinkstate;
	}
	
	public Integer getTpsCraftstate() {
		return tpsCraftstate;
	}

	public void setTpsCraftstate(Integer tpsCraftstate) {
		this.tpsCraftstate = tpsCraftstate;
	}
	
	public Integer getTpsPhotostate() {
		return tpsPhotostate;
	}

	public void setTpsPhotostate(Integer tpsPhotostate) {
		this.tpsPhotostate = tpsPhotostate;
	}
	
	@Length(min=0, max=100, message="外观长度必须介于 0 和 100 之间")
	public String getTpsExteriorname() {
		return tpsExteriorname;
	}

	public void setTpsExteriorname(String tpsExteriorname) {
		this.tpsExteriorname = tpsExteriorname;
	}
	
	@Length(min=0, max=2000, message="外观-特殊要求长度必须介于 0 和 2000 之间")
	public String getTpsExteriorsc() {
		return tpsExteriorsc;
	}

	public void setTpsExteriorsc(String tpsExteriorsc) {
		this.tpsExteriorsc = tpsExteriorsc;
	}
	
	@Length(min=0, max=2000, message="外观-检测方法长度必须介于 0 和 2000 之间")
	public String getTpsExteriordm() {
		return tpsExteriordm;
	}

	public void setTpsExteriordm(String tpsExteriordm) {
		this.tpsExteriordm = tpsExteriordm;
	}
	
	public Integer getTpsJincheng() {
		return tpsJincheng;
	}

	public void setTpsJincheng(Integer tpsJincheng) {
		this.tpsJincheng = tpsJincheng;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTpsCreatetime() {
		return tpsCreatetime;
	}

	public void setTpsCreatetime(Date tpsCreatetime) {
		this.tpsCreatetime = tpsCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTpsUpdatetime() {
		return tpsUpdatetime;
	}

	public void setTpsUpdatetime(Date tpsUpdatetime) {
		this.tpsUpdatetime = tpsUpdatetime;
	}
	
}