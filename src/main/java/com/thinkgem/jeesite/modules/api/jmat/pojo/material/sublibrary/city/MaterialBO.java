package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city;

import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.PhotoUpload;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 材料相关信息业务对象(数据更新实体)
 * @author ljc
 * @createTime 2018-6-22 11:43:39
 * @action 子库 地区 档次 保存品牌（分项）
 */
@ApiModel(value = "材料相关信息业务对象(数据更新实体)")
public class MaterialBO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("材料ID")
    private String  mID;
    @ApiModelProperty("四级科目ID")
    private String  mTreeFour;
    @ApiModelProperty("材料母库ID")
    private String  mTSID;
    @ApiModelProperty("城市ID")
    private Integer  mCityID;
    @ApiModelProperty("档次（1:A档，2:B档，4:C档）")
    private Integer  mLevel;
    @ApiModelProperty("是否已编辑：1是，-1代表地方提交审核       （默认0）（为0时：母库更新数据同步子库内容）")
    private Integer  mUpdateState = 0;
    @ApiModelProperty("用户类型 6 集团材料")
    private Integer  userType;
    @ApiModelProperty("操作人卡号")
    private String  userNo;
    @ApiModelProperty("品牌名称")
    private String  mBrandName;
    @ApiModelProperty("品牌描述")
    private String  mMatDescription;
    @ApiModelProperty("品牌型号 例:M-ZFZD-E5W1035")
    private String  mBrandType;
    @ApiModelProperty("成本价")
    private Double  mCostPrice;
    @ApiModelProperty("报价")
    private Double  mQuotesPrice;
    @ApiModelProperty("安装")
    private Double  mInstallPrice;
    @ApiModelProperty("是否推荐：1推荐    （默认0）")
    private Integer  mHostState;
    @ApiModelProperty("是否首页推荐：1推荐  （默认0）")
    private Integer  mHomeHostState;
    @ApiModelProperty("材料类型：1品牌材料，2瑞祥专供材料    （默认0）")
    private Integer  mMatType;
    @ApiModelProperty("保存类型:1品牌信息 2品牌型号 3入库操作 4审核通过 5审核打回")
    private Integer  saveType;
    @ApiModelProperty("是否已上架：1已上架，2预下架    （默认0）")
    private Integer mShoppingState;


    // 添加品牌相关
    @ApiModelProperty("图片上传")
    private List<PhotoUpload> photoItems;
    @ApiModelProperty("图片上传类型/图片/标题字符串,例: tspType + \"♣\" + img + \"♣\" + title + \"♠\";")
    private String strData;
    @ApiModelProperty("城市编码")
    private String cityCode;
    @ApiModelProperty("材料编码")
    private String matCode;

    // 审核品牌相关
    @ApiModelProperty("审核:打回备注")
    private String mRemark;

    // 材料小样相关
    @ApiModelProperty("小样类型：1实样，2画册  （默认1）")
    private Integer mSampleType;
    @ApiModelProperty("小样状态：0未,1有,2无 （默认0）")
    private Integer mssSampleStatus;
    @ApiModelProperty("上架状态: 0未,1已上架,2未上架 (默认0)")
    private Integer mssGroundingStatus;

    public MaterialBO() {
        super();
    }

    public Integer getmShoppingState() {
        return mShoppingState;
    }

    public void setmShoppingState(Integer mShoppingState) {
        this.mShoppingState = mShoppingState;
    }

    public MaterialBO(String mID, Integer mSampleType) {
        this.mID = mID;
        this.mSampleType = mSampleType;
    }

    public Integer getMssSampleStatus() {
        return mssSampleStatus;
    }

    public void setMssSampleStatus(Integer mssSampleStatus) {
        this.mssSampleStatus = mssSampleStatus;
    }

    public Integer getMssGroundingStatus() {
        return mssGroundingStatus;
    }

    public void setMssGroundingStatus(Integer mssGroundingStatus) {
        this.mssGroundingStatus = mssGroundingStatus;
    }

    public Integer getmSampleType() {
        return mSampleType;
    }

    public void setmSampleType(Integer mSampleType) {
        this.mSampleType = mSampleType;
    }

    public String getmRemark() {
        return mRemark;
    }

    public MaterialBO setmRemark(String mRemark) {
        this.mRemark = mRemark;
        return this;
    }

    public String getMatCode() {
        return matCode;
    }

    public MaterialBO setMatCode(String matCode) {
        this.matCode = matCode;
        return this;
    }

    public String getCityCode() {
        return cityCode;
    }

    public MaterialBO setCityCode(String cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public List<PhotoUpload> getPhotoItems() {
        return photoItems;
    }

    public MaterialBO setPhotoItems(List<PhotoUpload> photoItems) {
        this.photoItems = photoItems;
        return this;
    }

    public String getStrData() {
        return strData;
    }

    public MaterialBO setStrData(String strData) {
        this.strData = strData;
        return this;
    }

    public String getmID() {
        return mID;
    }

    public MaterialBO setmID(String mID) {
        this.mID = mID;
        return this;
    }

    public String getmTreeFour() {
        return mTreeFour;
    }

    public MaterialBO setmTreeFour(String mTreeFour) {
        this.mTreeFour = mTreeFour;
        return this;
    }

    public String getmTSID() {
        if ("".equals(mTSID))
            mTSID = null;
        return mTSID;
    }

    public MaterialBO setmTSID(String mTSID) {
        this.mTSID = mTSID;
        return this;
    }

    public Integer getmCityID() {
        return mCityID;
    }

    public MaterialBO setmCityID(Integer mCityID) {
        this.mCityID = mCityID;
        return this;
    }

    public Integer getmLevel() {
        return mLevel;
    }

    public MaterialBO setmLevel(Integer mLevel) {
        this.mLevel = mLevel;
        return this;
    }

    public Integer getmUpdateState() {
        return mUpdateState;
    }

    public MaterialBO setmUpdateState(Integer mUpdateState) {
        this.mUpdateState = mUpdateState;
        return this;
    }

    public Integer getUserType() {
        return userType;
    }

    public MaterialBO setUserType(Integer userType) {
        this.userType = userType;
        return this;
    }

    public String getUserNo() {
        return userNo;
    }

    public MaterialBO setUserNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    public String getmBrandName() {
        return mBrandName;
    }

    public MaterialBO setmBrandName(String mBrandName) {
        this.mBrandName = mBrandName;
        return this;
    }

    public String getmMatDescription() {
        return mMatDescription;
    }

    public MaterialBO setmMatDescription(String mMatDescription) {
        this.mMatDescription = mMatDescription;
        return this;
    }

    public String getmBrandType() {
        return mBrandType;
    }

    public MaterialBO setmBrandType(String mBrandType) {
        this.mBrandType = mBrandType;
        return this;
    }

    public Double getmCostPrice() {
        return mCostPrice;
    }

    public MaterialBO setmCostPrice(Double mCostPrice) {
        this.mCostPrice = mCostPrice;
        return this;
    }

    public Double getmQuotesPrice() {
        return mQuotesPrice;
    }

    public MaterialBO setmQuotesPrice(Double mQuotesPrice) {
        this.mQuotesPrice = mQuotesPrice;
        return this;
    }

    public Double getmInstallPrice() {
        return mInstallPrice;
    }

    public MaterialBO setmInstallPrice(Double mInstallPrice) {
        this.mInstallPrice = mInstallPrice;
        return this;
    }

    public Integer getmHostState() {
        return mHostState;
    }

    public MaterialBO setmHostState(Integer mHostState) {
        this.mHostState = mHostState;
        return this;
    }

    public Integer getmHomeHostState() {
        return mHomeHostState;
    }

    public MaterialBO setmHomeHostState(Integer mHomeHostState) {
        this.mHomeHostState = mHomeHostState;
        return this;
    }

    public Integer getmMatType() {
        return mMatType;
    }

    public MaterialBO setmMatType(Integer mMatType) {
        this.mMatType = mMatType;
        return this;
    }

    public Integer getSaveType() {
        return saveType;
    }

    public MaterialBO setSaveType(Integer saveType) {
        this.saveType = saveType;
        return this;
    }
}
