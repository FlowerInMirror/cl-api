/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 材料商自定义分项表Entity
 * @author ljc
 * @version 2018-08-23
 */
public class MajorMaterialDealerItem  {
	
	private static final long serialVersionUID = 1L;
	private Integer mmdiId;		// ID 标识
	private Integer mmdiItemid;		// 属性类别项ID
	private String mmdiSpid;		// 专项产品ID
	private String mmdiName;		// 名称
	private Integer mmdiOrder;		// 排序 (默认999)
	private Integer mmdiType;		// 类型:0集团添加项,1材料商专属类别项 (默认1)
	private Integer mmdiStatus;		// 进程：0正常、1删除  （默认0）
	private Date mmdiCreatetime;		// 创建时间    （默认getdate()）
	private Date mmdiUpdatetime;		// 更新时间
	
	public MajorMaterialDealerItem() {
		super();
	}

    public MajorMaterialDealerItem(Integer mmdiItemid, String mmdiSpid, String mmdiName) {
        this.mmdiItemid = mmdiItemid;
        this.mmdiSpid = mmdiSpid;
        this.mmdiName = mmdiName;
    }

    public Integer getMmdiId() {
		return mmdiId;
	}

	public void setMmdiId(Integer mmdiId) {
		this.mmdiId = mmdiId;
	}

    public Integer getMmdiItemid() {
        return mmdiItemid;
    }

    public MajorMaterialDealerItem setMmdiItemid(Integer mmdiItemid) {
        this.mmdiItemid = mmdiItemid;
        return this;
    }

    public String getMmdiSpid() {
		return mmdiSpid;
	}

	public void setMmdiSpid(String mmdiSpid) {
		this.mmdiSpid = mmdiSpid;
	}
	
	@Length(min=0, max=128, message="名称长度必须介于 0 和 128 之间")
	public String getMmdiName() {
		return mmdiName;
	}

	public void setMmdiName(String mmdiName) {
		this.mmdiName = mmdiName;
	}
	
	public Integer getMmdiOrder() {
		return mmdiOrder;
	}

	public void setMmdiOrder(Integer mmdiOrder) {
		this.mmdiOrder = mmdiOrder;
	}
	
	public Integer getMmdiType() {
		return mmdiType;
	}

	public void setMmdiType(Integer mmdiType) {
		this.mmdiType = mmdiType;
	}
	
	public Integer getMmdiStatus() {
		return mmdiStatus;
	}

	public void setMmdiStatus(Integer mmdiStatus) {
		this.mmdiStatus = mmdiStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMmdiCreatetime() {
		return mmdiCreatetime;
	}

	public void setMmdiCreatetime(Date mmdiCreatetime) {
		this.mmdiCreatetime = mmdiCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMmdiUpdatetime() {
		return mmdiUpdatetime;
	}

	public void setMmdiUpdatetime(Date mmdiUpdatetime) {
		this.mmdiUpdatetime = mmdiUpdatetime;
	}
	
}