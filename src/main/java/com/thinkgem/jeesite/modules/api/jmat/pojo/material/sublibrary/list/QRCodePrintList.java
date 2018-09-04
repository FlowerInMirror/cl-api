package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 二维码打印列表 包装类
 * @author     ljc
 * @createTime 2018-7-11 15:47:54
 */
@ApiModel(value = "二维码打印列表")
public class QRCodePrintList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("材料ID")
    private String matID;
    @ApiModelProperty("材料名称")
    private String matName;
    @ApiModelProperty("材料编码")
    private String matCode;
    @ApiModelProperty("材料品牌类型(A/B/C)")
    private String matLevel;
    @ApiModelProperty("材料单位")
    private String matUnit;
    @ApiModelProperty("材料品牌名称")
    private String matBrandName;
    @ApiModelProperty("材料品牌拼音")
    private String matBrandNamePinYin;
    @ApiModelProperty("材料规格名称")
    private String matSpecName;
    @ApiModelProperty("材料商城地址")
    private String matMallUrl;
    @ApiModelProperty("材料报价")
    private String matQuotedPrice;

    public QRCodePrintList() {
        super();
    }

    public String getMatID() {
        return matID;
    }

    public void setMatID(String matID) {
        this.matID = matID;
    }

    public String getMatBrandNamePinYin() {
        return matBrandNamePinYin;
    }

    public void setMatBrandNamePinYin(String matBrandNamePinYin) {
        this.matBrandNamePinYin = matBrandNamePinYin;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatLevel() {
        return matLevel;
    }

    public void setMatLevel(String matLevel) {
        this.matLevel = matLevel;
    }

    public String getMatUnit() {
        return matUnit;
    }

    public void setMatUnit(String matUnit) {
        this.matUnit = matUnit;
    }

    public String getMatBrandName() {
        return matBrandName;
    }

    public void setMatBrandName(String matBrandName) {
        this.matBrandName = matBrandName;
    }

    public String getMatSpecName() {
        return matSpecName;
    }

    public void setMatSpecName(String matSpecName) {
        this.matSpecName = matSpecName;
    }

    public String getMatMallUrl() {
        return matMallUrl;
    }

    public void setMatMallUrl(String matMallUrl) {
        this.matMallUrl = matMallUrl;
    }

    public String getMatQuotedPrice() {
        return matQuotedPrice;
    }

    public void setMatQuotedPrice(String matQuotedPrice) {
        this.matQuotedPrice = matQuotedPrice;
    }
}
