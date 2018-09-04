/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.major.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 主营属性类别项表Entity
 * @author ljc
 * @version 2018-08-17
 */
public class MajorCampTypeItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer mctiId;		// ID 标识
	private Integer mctiMctid;		// 属性类别ID
    private Integer mctiDealerID; // 材料商ID
	private String mctiName;		// 名称
	private Integer mctiOrder;		// 排序 (自增)
	private Integer mctiType;		// 类型:0集团添加项,1材料商专属类别项 (默认0)
	private Integer mctiStatus;		// 进程：0正常、1删除  （默认0）
	private Date mctiCreatetime;		// 创建时间    （默认getdate()）
	private Date mctiUpdatetime;		// 更新时间
	

	public MajorCampTypeItem() {
		super();
	}


    public MajorCampTypeItem(Integer mctiMctid) {
        this.mctiMctid = mctiMctid;
    }

    public Integer getMctiType() {
        return mctiType;
    }

    public MajorCampTypeItem setMctiType(Integer mctiType) {
        this.mctiType = mctiType;
        return this;
    }

    public Integer getMctiDealerID() {
        return mctiDealerID;
    }

    public MajorCampTypeItem setMctiDealerID(Integer mctiDealerID) {
        this.mctiDealerID = mctiDealerID;
        return this;
    }

    @NotNull(message="ID 标识不能为空")
	public Integer getMctiId() {
		return mctiId;
	}

	public void setMctiId(Integer mctiId) {
		this.mctiId = mctiId;
	}
	
	public Integer getMctiMctid() {
		return mctiMctid;
	}

	public void setMctiMctid(Integer mctiMctid) {
		this.mctiMctid = mctiMctid;
	}
	
	public String getMctiName() {
		return mctiName;
	}

	public void setMctiName(String mctiName) {
		this.mctiName = mctiName;
	}
	
	public Integer getMctiOrder() {
		return mctiOrder;
	}

	public void setMctiOrder(Integer mctiOrder) {
		this.mctiOrder = mctiOrder;
	}

    @JsonIgnore
	public Integer getMctiStatus() {
		return mctiStatus;
	}

	public void setMctiStatus(Integer mctiStatus) {
		this.mctiStatus = mctiStatus;
	}

    @JsonIgnore
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMctiCreatetime() {
		return mctiCreatetime;
	}

	public void setMctiCreatetime(Date mctiCreatetime) {
		this.mctiCreatetime = mctiCreatetime;
	}

    @JsonIgnore
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMctiUpdatetime() {
		return mctiUpdatetime;
	}

	public void setMctiUpdatetime(Date mctiUpdatetime) {
		this.mctiUpdatetime = mctiUpdatetime;
	}


	// "=================== 附加属性 ======================"

    // 针对材料商自定义分表:返回其主键ID 类型为1(0集团添加项,1材料商专属类别项 (默认0)),用于编辑更新名称
    private String mmdiID;

    public String getMmdiID() {
        return mmdiID;
    }

    public MajorCampTypeItem setMmdiID(String mmdiID) {
        this.mmdiID = mmdiID;
        return this;
    }
}