/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 材料商材料关联表Entity
 * @author ljc
 * @version 2018-05-09
 */
public class MaterialToSuppliers extends DataEntity<MaterialToSuppliers> {
	
	private static final long serialVersionUID = 1L;
	private String msId;		// ID
	private String msMatid;		// 材料ID
	private Integer msUserid;		// 材料商ID
	private String msPrice;		// 材料商价格
	private Integer msJincheng;		// 进程：0正常、1删除  （默认0）
	private Date msCreatetime;		// 创建时间    （默认getdate()）
	private Date msUpdatetime;		// 更新时间
	
	public MaterialToSuppliers() {
		super();
	}

	public MaterialToSuppliers(String id){
		super(id);
	}

	public String getMsId() {
		return msId;
	}

	public void setMsId(String msId) {
		this.msId = msId;
	}
	
	public String getMsMatid() {
		return msMatid;
	}

	public void setMsMatid(String msMatid) {
		this.msMatid = msMatid;
	}
	
	public Integer getMsUserid() {
		return msUserid;
	}

	public void setMsUserid(Integer msUserid) {
		this.msUserid = msUserid;
	}
	
	public String getMsPrice() {
		return msPrice;
	}

	public void setMsPrice(String msPrice) {
		this.msPrice = msPrice;
	}
	
	public Integer getMsJincheng() {
		return msJincheng;
	}

	public void setMsJincheng(Integer msJincheng) {
		this.msJincheng = msJincheng;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMsCreatetime() {
		return msCreatetime;
	}

	public void setMsCreatetime(Date msCreatetime) {
		this.msCreatetime = msCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMsUpdatetime() {
		return msUpdatetime;
	}

	public void setMsUpdatetime(Date msUpdatetime) {
		this.msUpdatetime = msUpdatetime;
	}
	
}