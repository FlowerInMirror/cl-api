/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 专项产品Entity
 * @author ljc
 * @version 2018-08-08
 */
public class SpecialProduct implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String spID;		// 专项产品ID
	private Integer spDealerID;		// 材料经销商ID
	private String spMajorTreeTwoID;		// 所属主营二级科目ID
    private Integer spCityID; // 城市ID
	private String spProName;		// 产品名称
	private Integer spProUnitID;		// 产品单位ID（材料库单位）
	private String spProDepict;		// 产品描述
	private Integer spProType;		// 产品类型备用字段(默认0)
	private String spProDepictData;		// 产品详情数据(来自页面富文本编辑器HTML)
	private Integer spStatus;		// 进程：0正常、1删除 、2下架 （默认0）
	private Date spCreateTime;		// 创建时间    （默认getdate()）
	private Date spUpdateTime;		// 更新时间
	
	public SpecialProduct() {
		super();
	}

	// 有参:专项产品添加:ID/主营二级科目ID/材料商ID/名称/单位/详情描述
    public SpecialProduct(String spID, Integer spDealerID, String spMajorTreeTwoID, String spProName, Integer spProUnitID, String spProDepictData) {
        this.spID = spID;
        this.spDealerID = spDealerID;
        this.spMajorTreeTwoID = spMajorTreeTwoID;
        this.spProName = spProName;
        this.spProUnitID = spProUnitID;
        this.spProDepictData = spProDepictData;
    }

    // 有参:检索 专项产品
    public SpecialProduct(String spID) {
        this.spID = spID;
    }

    // 有参:检索 专项产品集
    public SpecialProduct(Integer spDealerID, Integer spCityID) {
        this.spDealerID = spDealerID;
        this.spCityID = spCityID;
    }


    public String getSpID() {
		return spID;
	}

    public SpecialProduct setSpID(String spID) {
        this.spID = spID;
        return this;
    }

    public Integer getSpCityID() {
        return spCityID;
    }

    public SpecialProduct setSpCityID(Integer spCityID) {
        this.spCityID = spCityID;
        return this;
    }

    public Integer getSpDealerID() {
		return spDealerID;
	}

	public void setSpDealerID(Integer spDealerID) {
		this.spDealerID = spDealerID;
	}
	
	public String getSpMajorTreeTwoID() {
		return spMajorTreeTwoID;
	}

	public void setSpMajorTreeTwoID(String spMajorTreeTwoID) {
		this.spMajorTreeTwoID = spMajorTreeTwoID;
	}
	
	public String getSpProName() {
		return spProName;
	}

	public void setSpProName(String spProName) {
		this.spProName = spProName;
	}
	
	public Integer getSpProUnitID() {
		return spProUnitID;
	}

	public void setSpProUnitID(Integer spProUnitID) {
		this.spProUnitID = spProUnitID;
	}
	
	public String getSpProDepict() {
		return spProDepict;
	}

	public void setSpProDepict(String spProDepict) {
		this.spProDepict = spProDepict;
	}
	
	public Integer getSpProType() {
		return spProType;
	}

	public void setSpProType(Integer spProType) {
		this.spProType = spProType;
	}
	
	public String getSpProDepictData() {
		return spProDepictData;
	}

	public void setSpProDepictData(String spProDepictData) {
		this.spProDepictData = spProDepictData;
	}
	
	public Integer getSpStatus() {
		return spStatus;
	}

	public void setSpStatus(Integer spStatus) {
		this.spStatus = spStatus;
	}
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSpCreateTime() {
		return spCreateTime;
	}

	public void setSpCreateTime(Date spCreateTime) {
		this.spCreateTime = spCreateTime;
	}
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSpUpdateTime() {
		return spUpdateTime;
	}

	public void setSpUpdateTime(Date spUpdateTime) {
		this.spUpdateTime = spUpdateTime;
	}


}