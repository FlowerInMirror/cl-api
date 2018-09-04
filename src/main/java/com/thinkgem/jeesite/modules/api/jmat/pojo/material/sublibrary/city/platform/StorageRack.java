package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform;

import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list.QRCodePrintList;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 平台货架(子库地区>平台>小样信息(三段弹出)货架项)
 * @author ljc
 * @createTime 2018-7-23 11:37:07
 */
@ApiModel(value = "平台货架(子库地区>平台>小样信息(三段弹出)货架项)")
public class StorageRack extends QRCodePrintList {

    @ApiModelProperty("小样类型：1需要，2不需要  （默认1）")
    private Integer sampleType;
    @ApiModelProperty("小样状态：0未,1有,2无 （默认0）")
    private Integer sampleStatus;
    @ApiModelProperty("上架状态: 0未,1已上架,2未上架 (默认0)")
    private Integer groundingStatus;

    public StorageRack() {
        super();
    }

    public Integer getSampleType() {
        return sampleType;
    }

    public void setSampleType(Integer sampleType) {
        this.sampleType = sampleType;
    }

    public Integer getSampleStatus() {
        return sampleStatus;
    }

    public void setSampleStatus(Integer sampleStatus) {
        this.sampleStatus = sampleStatus;
    }

    public Integer getGroundingStatus() {
        return groundingStatus;
    }

    public void setGroundingStatus(Integer groundingStatus) {
        this.groundingStatus = groundingStatus;
    }
}
