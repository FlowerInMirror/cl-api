package com.thinkgem.jeesite.modules.api.em.material.entity.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 工程经理.材料 列表
 * @author     ljc
 * @createTime 2018-8-29 21:28:11
 */
@ApiModel(value="工程经理.材料 列表")
public class EMMaterialListVo implements Serializable {

    private static final long serialVersionUID = -6477048508792046100L;

    @ApiModelProperty("行号")
    private int rowNo;
    @ApiModelProperty("材料名称")
    private String matName;
    @ApiModelProperty("档次编码")
    private String levelCode;
    @ApiModelProperty("品牌名称")
    private String brandName;
    @ApiModelProperty("型号名称")
    private String brandType;
    @ApiModelProperty("LOGO图数 0未,1完")
    private int logoPicCount;
    @ApiModelProperty("主图总数 0未,1完")
    private int mainPicCount;
    @ApiModelProperty("型号图总数 0未,1完")
    private int typePicCount;
    @ApiModelProperty("材料材料商数量")
    private int mdCount;
    @ApiModelProperty("成本价")
    private double costPrice;
    @ApiModelProperty("报价")
    private double quotesPrice;
    @ApiModelProperty("交易最低价")
    private double mdMinPrice;
    @ApiModelProperty("交易最高价")
    private double mdMaxPrice;

    public EMMaterialListVo() {
        super();
    }

    public int getRowNo() {
        return rowNo;
    }

    public EMMaterialListVo setRowNo(int rowNo) {
        this.rowNo = rowNo;
        return this;
    }

    public String getMatName() {
        return matName;
    }

    public EMMaterialListVo setMatName(String matName) {
        this.matName = matName;
        return this;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public EMMaterialListVo setLevelCode(String levelCode) {
        this.levelCode = levelCode;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public EMMaterialListVo setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public String getBrandType() {
        return brandType;
    }

    public EMMaterialListVo setBrandType(String brandType) {
        this.brandType = brandType;
        return this;
    }

    public int getLogoPicCount() {
        return logoPicCount;
    }

    public EMMaterialListVo setLogoPicCount(int logoPicCount) {
        this.logoPicCount = logoPicCount;
        return this;
    }

    public int getMainPicCount() {
        return mainPicCount;
    }

    public EMMaterialListVo setMainPicCount(int mainPicCount) {
        this.mainPicCount = mainPicCount;
        return this;
    }

    public int getTypePicCount() {
        return typePicCount;
    }

    public EMMaterialListVo setTypePicCount(int typePicCount) {
        this.typePicCount = typePicCount;
        return this;
    }

    public int getMdCount() {
        return mdCount;
    }

    public EMMaterialListVo setMdCount(int mdCount) {
        this.mdCount = mdCount;
        return this;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public EMMaterialListVo setCostPrice(double costPrice) {
        this.costPrice = costPrice;
        return this;
    }

    public double getQuotesPrice() {
        return quotesPrice;
    }

    public EMMaterialListVo setQuotesPrice(double quotesPrice) {
        this.quotesPrice = quotesPrice;
        return this;
    }

    public double getMdMinPrice() {
        return mdMinPrice;
    }

    public EMMaterialListVo setMdMinPrice(double mdMinPrice) {
        this.mdMinPrice = mdMinPrice;
        return this;
    }

    public double getMdMaxPrice() {
        return mdMaxPrice;
    }

    public EMMaterialListVo setMdMaxPrice(double mdMaxPrice) {
        this.mdMaxPrice = mdMaxPrice;
        return this;
    }
}
