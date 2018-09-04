/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 材料基础信息Entity
 * @author ljc
 * @version 2018-03-16
 */
public class TreeBaseInfo extends DataEntity<TreeBaseInfo> {
	
	private static final long serialVersionUID = 1L;
	private String tbiId;		// ID
	private String tbiTreeid;		// 树ID
	private Integer tbiUnit;		// 单位
	private String tbiMatdescription;		// 材料描述
	private String tbiMatfunction;		// 功能
	private String tbiExteriorname;		// 官方标准-外观
	private String tbiExteriorsc;		// 官方标准-外观-特殊要求
	private String tbiExteriordm;		// 官方标准-外观-检测方法
	private String tbiMockupsamplingremark;		// 小样标准-取样方法
	private Integer tbiPstype;		// 包装标准-类别：1瑞祥标准，2合作商标准
	private String tbiPsmaterialquality;		// 包装标准-包装材质
	private String tbiPspackageremark;		// 包装标准-源文件
	private String tbiPsmarkremark;		// 包装标准-包装说明
	private Integer tbiStatus;		// 进程：0正常、1删除  （默认0）
	private Date tbiCreatetime;		// 创建时间    （默认getdate()）
	private Date tbiUpdatetime;		// 更新时间
	
	public TreeBaseInfo() {
		super();
	}

	public TreeBaseInfo(String id){
		super(id);
	}

	public String getTbiId() {
		return tbiId;
	}

	public void setTbiId(String tbiId) {
		this.tbiId = tbiId;
	}
	
	public String getTbiTreeid() {
		return tbiTreeid;
	}

	public void setTbiTreeid(String tbiTreeid) {
		this.tbiTreeid = tbiTreeid;
	}
	
	public Integer getTbiUnit() {
		return tbiUnit;
	}

	public void setTbiUnit(Integer tbiUnit) {
		this.tbiUnit = tbiUnit;
	}
	
	@Length(min=0, max=2000, message="材料描述长度必须介于 0 和 2000 之间")
	public String getTbiMatdescription() {
		return tbiMatdescription;
	}

	public void setTbiMatdescription(String tbiMatdescription) {
		this.tbiMatdescription = tbiMatdescription;
	}
	
	@Length(min=0, max=2000, message="功能长度必须介于 0 和 2000 之间")
	public String getTbiMatfunction() {
		return tbiMatfunction;
	}

	public void setTbiMatfunction(String tbiMatfunction) {
		this.tbiMatfunction = tbiMatfunction;
	}
	
	@Length(min=0, max=2000, message="官方标准-外观长度必须介于 0 和 2000 之间")
	public String getTbiExteriorname() {
		return tbiExteriorname;
	}

	public void setTbiExteriorname(String tbiExteriorname) {
		this.tbiExteriorname = tbiExteriorname;
	}
	
	@Length(min=0, max=2000, message="官方标准-外观-特殊要求长度必须介于 0 和 2000 之间")
	public String getTbiExteriorsc() {
		return tbiExteriorsc;
	}

	public void setTbiExteriorsc(String tbiExteriorsc) {
		this.tbiExteriorsc = tbiExteriorsc;
	}
	
	@Length(min=0, max=2000, message="官方标准-外观-检测方法长度必须介于 0 和 2000 之间")
	public String getTbiExteriordm() {
		return tbiExteriordm;
	}

	public void setTbiExteriordm(String tbiExteriordm) {
		this.tbiExteriordm = tbiExteriordm;
	}
	
	@Length(min=0, max=2000, message="小样标准-取样方法长度必须介于 0 和 2000 之间")
	public String getTbiMockupsamplingremark() {
		return tbiMockupsamplingremark;
	}

	public void setTbiMockupsamplingremark(String tbiMockupsamplingremark) {
		this.tbiMockupsamplingremark = tbiMockupsamplingremark;
	}
	
	public Integer getTbiPstype() {
		return tbiPstype;
	}

	public void setTbiPstype(Integer tbiPstype) {
		this.tbiPstype = tbiPstype;
	}
	
	@Length(min=0, max=2000, message="包装标准-包装材质长度必须介于 0 和 2000 之间")
	public String getTbiPsmaterialquality() {
		return tbiPsmaterialquality;
	}

	public void setTbiPsmaterialquality(String tbiPsmaterialquality) {
		this.tbiPsmaterialquality = tbiPsmaterialquality;
	}
	
	@Length(min=0, max=255, message="包装标准-源文件长度必须介于 0 和 255 之间")
	public String getTbiPspackageremark() {
		return tbiPspackageremark;
	}

	public void setTbiPspackageremark(String tbiPspackageremark) {
		this.tbiPspackageremark = tbiPspackageremark;
	}
	
	@Length(min=0, max=2000, message="包装标准-包装说明长度必须介于 0 和 2000 之间")
	public String getTbiPsmarkremark() {
		return tbiPsmarkremark;
	}

	public void setTbiPsmarkremark(String tbiPsmarkremark) {
		this.tbiPsmarkremark = tbiPsmarkremark;
	}
	
	public Integer getTbiStatus() {
		return tbiStatus;
	}

	public void setTbiStatus(Integer tbiStatus) {
		this.tbiStatus = tbiStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTbiCreatetime() {
		return tbiCreatetime;
	}

	public void setTbiCreatetime(Date tbiCreatetime) {
		this.tbiCreatetime = tbiCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTbiUpdatetime() {
		return tbiUpdatetime;
	}

	public void setTbiUpdatetime(Date tbiUpdatetime) {
		this.tbiUpdatetime = tbiUpdatetime;
	}
	
	//---附加属性---
	private String unitName;//单位名称
	
	//---组合对象---
	
	
	public String getUnitName() {
		return unitName;
	}
	
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
}