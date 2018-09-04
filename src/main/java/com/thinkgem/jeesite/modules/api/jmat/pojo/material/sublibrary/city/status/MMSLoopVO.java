package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.status;

import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.SublibrariesAList;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 材料管理系统回路包装类
 * @author ljc
 * @createTime 2018-7-24 21:50:25
 */
@ApiModel(value = " 材料管理系统回路包装类")
public class MMSLoopVO extends SublibrariesAList {

    @ApiModelProperty("材料分类(1成品，2非成品)")
    private Integer matClassify;
    @ApiModelProperty("单位ID")
    private Integer unitID;
    @ApiModelProperty("单位名称")
    private String unitName;

    public MMSLoopVO() {
        super();
    }

    public Integer getMatClassify() {
        return matClassify;
    }

    public void setMatClassify(Integer matClassify) {
        this.matClassify = matClassify;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
