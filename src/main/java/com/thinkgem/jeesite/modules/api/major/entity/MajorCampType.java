/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.major.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 主营属性类别表Entity
 * @author ljc
 * @version 2018-08-17
 */
public class MajorCampType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer mctId;		// ID 标识
	private String mctTreetwoid;		// 主营二级科目ID
	private String mctName;		// 名称
	private Integer mctOrder;		// 排序 (自增)
	private Integer mctStatus;		// 进程：0正常、1删除  （默认0）
	private Date mctCreatetime;		// 创建时间    （默认getdate()）
	private Date mctUpdatetime;		// 更新时间
	
	public MajorCampType() {
		super();
	}

    public MajorCampType (String mctTreetwoid) {
        this.mctTreetwoid = mctTreetwoid;
    }

    @NotNull(message="ID 标识不能为空")
	public Integer getMctId() {
		return mctId;
	}

	public void setMctId(Integer mctId) {
		this.mctId = mctId;
	}
	
	public String getMctTreetwoid() {
		return mctTreetwoid;
	}

	public void setMctTreetwoid(String mctTreetwoid) {
		this.mctTreetwoid = mctTreetwoid;
	}
	
	@Length(min=0, max=32, message="名称长度必须介于 0 和 32 之间")
	public String getMctName() {
		return mctName;
	}

	public void setMctName(String mctName) {
		this.mctName = mctName;
	}
	
	public Integer getMctOrder() {
		return mctOrder;
	}

	public void setMctOrder(Integer mctOrder) {
		this.mctOrder = mctOrder;
	}

    @JsonIgnore
	public Integer getMctStatus() {
		return mctStatus;
	}

	public void setMctStatus(Integer mctStatus) {
		this.mctStatus = mctStatus;
	}

    @JsonIgnore
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMctCreatetime() {
		return mctCreatetime;
	}

	public void setMctCreatetime(Date mctCreatetime) {
		this.mctCreatetime = mctCreatetime;
	}

    @JsonIgnore
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMctUpdatetime() {
		return mctUpdatetime;
	}

	public void setMctUpdatetime(Date mctUpdatetime) {
		this.mctUpdatetime = mctUpdatetime;
	}
	
}