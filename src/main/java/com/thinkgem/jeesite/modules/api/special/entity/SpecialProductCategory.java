/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.entity;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 专项产品类别表Entity
 * @author ljc
 * @version 2018-08-24
 */
public class SpecialProductCategory implements Serializable {

    private static final long serialVersionUID = -3045623366762344242L;

    private Integer spcId;		// ID
	private String spcSpid;		// 专项产品ID
	private Integer spcMctid;		// 主营属性类别ID
	private String spcMctname;		// 主营属性类别名称
	private Integer spcItemid;		// 主营属性类别项ID
	private Integer spcType;		// 类型(针对类别项):0集团录入,1材料商自定义项 (默认0)
	private Integer spcStatus;		// 进程：0正常、1删除 、2下架 （默认0）
	private Date spcCreatetime;		// 创建时间    （默认getdate()）
	private Date spcUpdatetime;		// 更新时间
	
	public SpecialProductCategory() {
		super();
	}


    public String getSpcMctname() {
        return spcMctname;
    }

    public SpecialProductCategory setSpcMctname(String spcMctname) {
        this.spcMctname = spcMctname;
        return this;
    }

    public Integer getSpcId() {
		return spcId;
	}

	public void setSpcId(Integer spcId) {
		this.spcId = spcId;
	}
	
	public String getSpcSpid() {
		return spcSpid;
	}

	public void setSpcSpid(String spcSpid) {
		this.spcSpid = spcSpid;
	}
	
	public Integer getSpcMctid() {
		return spcMctid;
	}

	public void setSpcMctid(Integer spcMctid) {
		this.spcMctid = spcMctid;
	}
	
	public Integer getSpcItemid() {
		return spcItemid;
	}

	public void setSpcItemid(Integer spcItemid) {
		this.spcItemid = spcItemid;
	}
	
	public Integer getSpcType() {
		return spcType;
	}

	public void setSpcType(Integer spcType) {
		this.spcType = spcType;
	}
	
	public Integer getSpcStatus() {
		return spcStatus;
	}

	public void setSpcStatus(Integer spcStatus) {
		this.spcStatus = spcStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSpcCreatetime() {
		return spcCreatetime;
	}

	public void setSpcCreatetime(Date spcCreatetime) {
		this.spcCreatetime = spcCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSpcUpdatetime() {
		return spcUpdatetime;
	}

	public void setSpcUpdatetime(Date spcUpdatetime) {
		this.spcUpdatetime = spcUpdatetime;
	}
	
}