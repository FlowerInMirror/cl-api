package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 图片上传POJO
 * 
 * @author     ljc
 * @createTime 2018-6-15 14:38:23
 * @action    入参适配数据载体
 */
@ApiModel(value = "图片上传POJO(添加模型)")
public class PhotoUpload implements Serializable {

	private static final long serialVersionUID = 1L;
	// 材料照片相关
    @ApiModelProperty("材料母库照片ID")
	private String tspID;
    @ApiModelProperty("四级科目ID")
	private String tspTreeFour;
    @ApiModelProperty("材料ID")
	private String tspTSID;
    @ApiModelProperty("图片类型：1品牌LOGO，2品牌型号，3材料对比图，4产品主图，5材料资质，6小样图，7产品效果图，8品质细节图，9材料工艺；10照片展示自定义类，11材料约定(默认0)")
	private Integer tspType = 0;
    @ApiModelProperty("图片地址")
    private String tspPhotoURL;
    @ApiModelProperty("档次（1:A档，2:B档，4:C档）（可位运算|）")
    private Integer tspMatLevel;
    @ApiModelProperty("分类：1母库，2子库")
    private Integer tspClass = 0;
    @ApiModelProperty("参数ID(对比ID-主)（针对对比图）   启用")
    private Integer tspParaID;
    @ApiModelProperty("地区ID")
    private Integer tspCityID;
    @ApiModelProperty("标准ID（针对对比图）  （针对10类型 是分类ID--V_TypeInfo）")
    private String tspBrandID;
    @ApiModelProperty("用户类型:6集团材料")
	private Integer userType;
    @ApiModelProperty("操作人卡号")
	private String userNo;
    @ApiModelProperty("四级科目ID")
	private String treeID;
    @ApiModelProperty("城市ID")
	private Integer city;

    @ApiModelProperty("是否主图：1主图    （默认0）")
	private Integer tspMian;
    @ApiModelProperty("备注1（标题）")
	private String tspTitle;
    @ApiModelProperty("备注2（内容）")
	private String tspContent1;
    @ApiModelProperty("备注3（内容）")
	private String tspContent2;

    // 材料基础信息 包装照片相关
    @ApiModelProperty("包装标准-源文件(包装照片)")
    private String tbiPSPackageRemark;
    @ApiModelProperty("树ID(四级科目ID)")
    private String tbiTreeID;


    public Integer getTspMian() {
        return tspMian;
    }

    public void setTspMian(Integer tspMian) {
        this.tspMian = tspMian;
    }

    public String getTspTitle() {
        return tspTitle;
    }

    public void setTspTitle(String tspTitle) {
        this.tspTitle = tspTitle;
    }

    public String getTspContent1() {
        return tspContent1;
    }

    public void setTspContent1(String tspContent1) {
        this.tspContent1 = tspContent1;
    }

    public String getTspContent2() {
        return tspContent2;
    }

    public void setTspContent2(String tspContent2) {
        this.tspContent2 = tspContent2;
    }

    public String getTspID() {
        if ("".equals(tspID))
            tspID = null;
        return tspID;
    }

    public void setTspID(String tspID) {
        this.tspID = tspID;
    }

    public String getTspTreeFour() {
        return tspTreeFour;
    }

    public void setTspTreeFour(String tspTreeFour) {
        this.tspTreeFour = tspTreeFour;
    }

    public String getTspTSID() {
        if ("".equals(tspTSID))
            tspTSID = null;
        return tspTSID;
    }

    public void setTspTSID(String tspTSID) {
        this.tspTSID = tspTSID;
    }

    public Integer getTspType() {
        return tspType;
    }

    public void setTspType(Integer tspType) {
        this.tspType = tspType;
    }

    public String getTspPhotoURL() {
        if ("".equals(tspPhotoURL))
            tspPhotoURL = null;
        return tspPhotoURL;
    }

    public void setTspPhotoURL(String tspPhotoURL) {
        this.tspPhotoURL = "".equals(tspPhotoURL) ? null : tspPhotoURL;
    }

    public Integer getTspMatLevel() {
        return tspMatLevel;
    }

    public void setTspMatLevel(Integer tspMatLevel) {
        this.tspMatLevel = tspMatLevel;
    }

    public Integer getTspClass() {
        return tspClass;
    }

    public void setTspClass(Integer tspClass) {
        this.tspClass = tspClass;
    }

    public Integer getTspParaID() {
        return tspParaID;
    }

    public void setTspParaID(Integer tspParaID) {
        this.tspParaID = tspParaID;
    }

    public Integer getTspCityID() {
        return tspCityID;
    }

    public void setTspCityID(Integer tspCityID) {
        this.tspCityID = tspCityID;
    }

    public String getTspBrandID() {
        return tspBrandID;
    }

    public void setTspBrandID(String tspBrandID) {
        this.tspBrandID = tspBrandID;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getTreeID() {
        return treeID;
    }

    public void setTreeID(String treeID) {
        this.treeID = treeID;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getTbiPSPackageRemark() {
        return tbiPSPackageRemark;
    }

    public void setTbiPSPackageRemark(String tbiPSPackageRemark) {
        this.tbiPSPackageRemark = tbiPSPackageRemark;
    }

    public String getTbiTreeID() {
        return tbiTreeID;
    }

    public void setTbiTreeID(String tbiTreeID) {
        this.tbiTreeID = tbiTreeID;
    }
}
