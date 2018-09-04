package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 材料基础数据
 *
 * @author ljc
 * @version 2018-5-19 18:14:09
 */
@ApiModel(value = "材料基础数据")
public class MatBaseData implements Serializable {

    private static final long serialVersionUID = 1L;
    // 科目数相关信息
    @ApiModelProperty("一级科目名")
    private String treeOneName;
    @ApiModelProperty("二级科目名")
    private String treeTwoName;
    @ApiModelProperty("材料名称")
    private String matName;
    @ApiModelProperty("规格名称")
    private String matSpec;
    @ApiModelProperty("规格编码")
    private String matSpecCode;
    @ApiModelProperty("排序（根据使用次数--项目）（针对第四级）")
    private Integer treeOrder;
    // 置顶信息
    @ApiModelProperty("第几页   （默认0）")
    private Integer topPageIndex;
    @ApiModelProperty("第几号   （默认0）")
    private Integer topPageNum;
    // 单位
    @ApiModelProperty("单位ID")
    private Integer unitID;
    @ApiModelProperty("单位名称")
    private String unit;
    // 是否成品类
    @ApiModelProperty("分类")
    private Integer classify;

    // 专项ID
    @ApiModelProperty("专项科目树ID,不是专项则为NULL")
    private String specialID;

    // 规格级信息(材料基础信息V_TreeBaseInfo)
    @ApiModelProperty("材料描述")
    private String matDescription;
    @ApiModelProperty("材料功能")
    private String matFunction;
    @ApiModelProperty("官方标准-外观")
    private String exteriorName;
    @ApiModelProperty("官方标准-外观-特殊要求")
    private String exteriorSC;
    @ApiModelProperty("小样标准-取样方法")
    private String mockUpSamplingRemark;
    @ApiModelProperty("包装标准-类别：1瑞祥标准，2合作商标准")
    private Integer psType;
    @ApiModelProperty("包装标准-包装材质")
    private String psMaterialQuality;
    @ApiModelProperty("包装标准-源文件(包装照片)")
    private String psPackageRemark;
    @ApiModelProperty("包装标准-包装说明")
    private String psMarkRemark;

    public MatBaseData() {
        super();
    }

    public String getSpecialID() {
        return specialID;
    }

    public void setSpecialID(String specialID) {
        this.specialID = specialID;
    }

    public String getTreeOneName() {
        return treeOneName;
    }

    public void setTreeOneName(String treeOneName) {
        this.treeOneName = treeOneName;
    }

    public String getTreeTwoName() {
        return treeTwoName;
    }

    public void setTreeTwoName(String treeTwoName) {
        this.treeTwoName = treeTwoName;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatSpec() {
        return matSpec;
    }

    public void setMatSpec(String matSpec) {
        this.matSpec = matSpec;
    }

    public String getMatSpecCode() {
        return matSpecCode;
    }

    public void setMatSpecCode(String matSpecCode) {
        this.matSpecCode = matSpecCode;
    }

    public Integer getTreeOrder() {
        return treeOrder;
    }

    public void setTreeOrder(Integer treeOrder) {
        this.treeOrder = treeOrder;
    }

    public Integer getTopPageIndex() {
        return topPageIndex;
    }

    public void setTopPageIndex(Integer topPageIndex) {
        this.topPageIndex = topPageIndex;
    }

    public Integer getTopPageNum() {
        return topPageNum;
    }

    public void setTopPageNum(Integer topPageNum) {
        this.topPageNum = topPageNum;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public String getMatDescription() {
        return matDescription;
    }

    public void setMatDescription(String matDescription) {
        this.matDescription = matDescription;
    }

    public String getMatFunction() {
        return matFunction;
    }

    public void setMatFunction(String matFunction) {
        this.matFunction = matFunction;
    }

    public String getExteriorName() {
        return exteriorName;
    }

    public void setExteriorName(String exteriorName) {
        this.exteriorName = exteriorName;
    }

    public String getExteriorSC() {
        return exteriorSC;
    }

    public void setExteriorSC(String exteriorSC) {
        this.exteriorSC = exteriorSC;
    }

    public String getMockUpSamplingRemark() {
        return mockUpSamplingRemark;
    }

    public void setMockUpSamplingRemark(String mockUpSamplingRemark) {
        this.mockUpSamplingRemark = mockUpSamplingRemark;
    }

    public Integer getPsType() {
        return psType;
    }

    public void setPsType(Integer psType) {
        this.psType = psType;
    }

    public String getPsMaterialQuality() {
        return psMaterialQuality;
    }

    public void setPsMaterialQuality(String psMaterialQuality) {
        this.psMaterialQuality = psMaterialQuality;
    }

    public String getPsPackageRemark() {
        return psPackageRemark;
    }

    public void setPsPackageRemark(String psPackageRemark) {
        this.psPackageRemark = psPackageRemark;
    }

    public String getPsMarkRemark() {
        return psMarkRemark;
    }

    public void setPsMarkRemark(String psMarkRemark) {
        this.psMarkRemark = psMarkRemark;
    }
}
