/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 材料子库Entity
 * @author ljc
 * @version 2018-03-16
 */
public class Material extends DataEntity<Material> {
	
	private static final long serialVersionUID = 1L;
	private String mId;		// ID
	private String mTreeone;		// 一级ID
	private String mTreetwo;		// 二级ID
	private String mTreethree;		// 三级ID（材料名称）
	private String mTreefour;		// 四级ID（规格）
	private String mTsid;		// 材料母库ID
	private Integer mLevel;		// 档次（1:A档，2:B档，4:C档）
	private Integer mCityid;		// 地区
	private String mCode;		// 材料编号
	private String mBrandid;		// 品牌ID
	private String mBrandname;		// 品牌名称（继承可改）
	private String mBrandtype;		// 型号（继承可改）
	private Double mCostprice;		// 成本价（继承可改）
	private Double mQuotesprice;		// 报价（继承可改）
	private Double mInstallprice;		// 安装（继承可改）
	private Integer mHoststate;		// 是否推荐：1推荐    （默认0）（继承可改）
	private Integer mHomehoststate;		// 是否首页推荐：1推荐  （默认0）（继承可改）
	private Integer mMattype;		// 材料类型：1品牌材料，2瑞祥专供材料    （默认0）（继承可改）
	private String mMatdescription;		// 材料描述（继承可改）
	private Integer mUpdatestate;		// 是否已编辑：1是        （默认0）（为0时：母库更新数据同步子库内容）
	private Integer mShoppingstate;		// 是否已上架：1已上架    （默认0）
	private Integer mJincheng;		// 进程：0正常、1删除  （默认0）
	private Date mCreatetime;		// 创建时间    （默认getdate()）
	private Date mUpdatetime;		// 更新时间
	private Date mToptime;		// 置顶时间
	private String mRemark;		// 备用字段1
	private String mRemark2;		// 备用字段2

    // 货架功能新增字段
    private Integer mSampleType; // 小样类型：1需要，2不需要  （默认1）

    public Integer getmSampleType() {
        return mSampleType;
    }

    public void setmSampleType(Integer mSampleType) {
        this.mSampleType = mSampleType;
    }

    public Material() {
		super();
	}

	public Material(String id){
		super(id);
	}

	public String getMId() {
		return mId;
	}

	public void setMId(String mId) {
		this.mId = mId;
	}

	public String getMTreeone() {
		return mTreeone;
	}

	public void setMTreeone(String mTreeone) {
		this.mTreeone = mTreeone;
	}

	public String getMTreetwo() {
		return mTreetwo;
	}

	public void setMTreetwo(String mTreetwo) {
		this.mTreetwo = mTreetwo;
	}

	public String getMTreethree() {
		return mTreethree;
	}

	public void setMTreethree(String mTreethree) {
		this.mTreethree = mTreethree;
	}

	public String getMTreefour() {
		return mTreefour;
	}

	public void setMTreefour(String mTreefour) {
		this.mTreefour = mTreefour;
	}

	public String getMTsid() {
		return mTsid;
	}

	public void setMTsid(String mTsid) {
		this.mTsid = mTsid;
	}

	public Integer getMLevel() {
		return mLevel;
	}

	public void setMLevel(Integer mLevel) {
		this.mLevel = mLevel;
	}

	public Integer getMCityid() {
		return mCityid;
	}

	public void setMCityid(Integer mCityid) {
		this.mCityid = mCityid;
	}

	public String getMCode() {
		return mCode;
	}

	public void setMCode(String mCode) {
		this.mCode = mCode;
	}

	public String getMBrandid() {
		return mBrandid;
	}

	public void setMBrandid(String mBrandid) {
		this.mBrandid = mBrandid;
	}

	public String getMBrandname() {
		return mBrandname;
	}

	public void setMBrandname(String mBrandname) {
		this.mBrandname = mBrandname;
	}

	public String getMBrandtype() {
		return mBrandtype;
	}

	public void setMBrandtype(String mBrandtype) {
		this.mBrandtype = mBrandtype;
	}

	public Double getMCostprice() {
		return mCostprice;
	}

	public void setMCostprice(Double mCostprice) {
		this.mCostprice = mCostprice;
	}

	public Double getMQuotesprice() {
		return mQuotesprice;
	}

	public void setMQuotesprice(Double mQuotesprice) {
		this.mQuotesprice = mQuotesprice;
	}

	public Double getMInstallprice() {
		return mInstallprice;
	}

	public void setMInstallprice(Double mInstallprice) {
		this.mInstallprice = mInstallprice;
	}

	public Integer getMHoststate() {
		return mHoststate;
	}

	public void setMHoststate(Integer mHoststate) {
		this.mHoststate = mHoststate;
	}

	public Integer getMHomehoststate() {
		return mHomehoststate;
	}

	public void setMHomehoststate(Integer mHomehoststate) {
		this.mHomehoststate = mHomehoststate;
	}

	public Integer getMMattype() {
		return mMattype;
	}

	public void setMMattype(Integer mMattype) {
		this.mMattype = mMattype;
	}

	public String getMMatdescription() {
		return mMatdescription;
	}

	public void setMMatdescription(String mMatdescription) {
		this.mMatdescription = mMatdescription;
	}

	public Integer getMUpdatestate() {
		return mUpdatestate;
	}

	public void setMUpdatestate(Integer mUpdatestate) {
		this.mUpdatestate = mUpdatestate;
	}

	public Integer getMShoppingstate() {
		return mShoppingstate;
	}

	public void setMShoppingstate(Integer mShoppingstate) {
		this.mShoppingstate = mShoppingstate;
	}

	public Integer getMJincheng() {
		return mJincheng;
	}

	public void setMJincheng(Integer mJincheng) {
		this.mJincheng = mJincheng;
	}

	public Date getMCreatetime() {
		return mCreatetime;
	}

	public void setMCreatetime(Date mCreatetime) {
		this.mCreatetime = mCreatetime;
	}

	public Date getMUpdatetime() {
		return mUpdatetime;
	}

	public void setMUpdatetime(Date mUpdatetime) {
		this.mUpdatetime = mUpdatetime;
	}

	public Date getMToptime() {
		return mToptime;
	}

	public void setMToptime(Date mToptime) {
		this.mToptime = mToptime;
	}

	public String getMRemark() {
		return mRemark;
	}

	public void setMRemark(String mRemark) {
		this.mRemark = mRemark;
	}

	public String getMRemark2() {
		return mRemark2;
	}

	public void setMRemark2(String mRemark2) {
		this.mRemark2 = mRemark2;
	}

	// ========== 附加属性
    private  String matCost; // 总成本

    // 品牌图片
    private  String brandLOGOPhotoID;
    private  String brandLOGOPhotoURL;
    // 型号图片
    private  String branTypePhotoID;
    private  String branTypePhotoURL;
    // 产品主图
    private  String branMainPhotoID;
    private  String branMainPhotoURL;


    public String getBrandLOGOPhotoID() {
        return brandLOGOPhotoID;
    }

    public void setBrandLOGOPhotoID(String brandLOGOPhotoID) {
        this.brandLOGOPhotoID = brandLOGOPhotoID;
    }

    public String getBrandLOGOPhotoURL() {
        return brandLOGOPhotoURL;
    }

    public void setBrandLOGOPhotoURL(String brandLOGOPhotoURL) {
        this.brandLOGOPhotoURL = brandLOGOPhotoURL;
    }

    public String getBranMainPhotoID() {
        return branMainPhotoID;
    }

    public void setBranMainPhotoID(String branMainPhotoID) {
        this.branMainPhotoID = branMainPhotoID;
    }

    public String getBranMainPhotoURL() {
        return branMainPhotoURL;
    }

    public void setBranMainPhotoURL(String branMainPhotoURL) {
        this.branMainPhotoURL = branMainPhotoURL;
    }

    public String getBranTypePhotoID() {
        return branTypePhotoID;
    }

    public void setBranTypePhotoID(String branTypePhotoID) {
        this.branTypePhotoID = branTypePhotoID;
    }

    public String getBranTypePhotoURL() {
        return branTypePhotoURL;
    }

    public void setBranTypePhotoURL(String branTypePhotoURL) {
        this.branTypePhotoURL = branTypePhotoURL;
    }

    public String getMatCost() {
        return matCost;
    }

    public void setMatCost(String matCost) {
        this.matCost = matCost;
    }
}