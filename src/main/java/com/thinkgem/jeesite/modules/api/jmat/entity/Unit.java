/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 材料单位Entity
 * @author ljc
 * @version 2018-03-23
 */
public class Unit extends DataEntity<Unit> {
	
	private static final long serialVersionUID = 1L;
	private Integer unitId;		// ID
	private String unitName;		// 单位名称
	private String unitCode;		// 单位缩写
	private Integer unitJincheng;		// 进程：0正常、1删除  （默认0）
	private Date unitCreatetime;		// 创建时间    （默认getdate()）
	private Date unitUpdatetime;		// 更新时间
	
	public Unit() {
		super();
	}

	public Unit(String id){
		super(id);
	}

	@NotNull(message="ID不能为空")
	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	
	@Length(min=0, max=10, message="单位名称长度必须介于 0 和 10 之间")
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	@Length(min=0, max=5, message="单位缩写长度必须介于 0 和 5 之间")
	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	
	public Integer getUnitJincheng() {
		return unitJincheng;
	}

	public void setUnitJincheng(Integer unitJincheng) {
		this.unitJincheng = unitJincheng;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUnitCreatetime() {
		return unitCreatetime;
	}

	public void setUnitCreatetime(Date unitCreatetime) {
		this.unitCreatetime = unitCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUnitUpdatetime() {
		return unitUpdatetime;
	}

	public void setUnitUpdatetime(Date unitUpdatetime) {
		this.unitUpdatetime = unitUpdatetime;
	}
	
}