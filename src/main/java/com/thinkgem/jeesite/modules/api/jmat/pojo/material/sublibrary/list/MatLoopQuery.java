package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 材料回路列表查询
 * @author ljc
 * @createTime 2018-7-31 19:04:34
 */
@ApiModel(value = "材料回路列表检索")
public class MatLoopQuery implements Serializable {

    private static final long serialVersionUID = -2227928064401998902L;

    @ApiModelProperty("城市ID")
    private Integer cityID;
    @ApiModelProperty("圈中标识 1圈中")
    private Integer circleFlag;
    @ApiModelProperty("总回路状态(1完2未)")
    private Integer totalFlag;
    @ApiModelProperty("平台回路(1完2未)")
    private Integer platformFlag;
    @ApiModelProperty("A档回路(1完2未)")
    private Integer aLevelFlag;
    @ApiModelProperty("B档回路(1完2未)")
    private Integer bLevelFlag;
    @ApiModelProperty("C档回路(1完2未)")
    private Integer cLevelFlag;
    @ApiModelProperty("处理类型(1正2异3问)")
    private Integer handleTypeFlag;
    @ApiModelProperty("处理日期(1当天2本周3本季4本年)")
    private Integer handleTimeFlag;

    // 处理时间检索相关字段
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;

    public MatLoopQuery() {
        super();
    }

    public Integer getCircleFlag() {
        return circleFlag;
    }

    public void setCircleFlag(Integer circleFlag) {
        this.circleFlag = circleFlag;
    }

    public Integer getHandleTypeFlag() {
        return handleTypeFlag;
    }

    public void setHandleTypeFlag(Integer handleTypeFlag) {
        this.handleTypeFlag = handleTypeFlag;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public Integer getTotalFlag() {
        return totalFlag;
    }

    public void setTotalFlag(Integer totalFlag) {
        this.totalFlag = totalFlag;
    }

    public Integer getPlatformFlag() {
        return platformFlag;
    }

    public void setPlatformFlag(Integer platformFlag) {
        this.platformFlag = platformFlag;
    }

    public Integer getaLevelFlag() {
        return aLevelFlag;
    }

    public void setaLevelFlag(Integer aLevelFlag) {
        this.aLevelFlag = aLevelFlag;
    }

    public Integer getbLevelFlag() {
        return bLevelFlag;
    }

    public void setbLevelFlag(Integer bLevelFlag) {
        this.bLevelFlag = bLevelFlag;
    }

    public Integer getcLevelFlag() {
        return cLevelFlag;
    }

    public void setcLevelFlag(Integer cLevelFlag) {
        this.cLevelFlag = cLevelFlag;
    }

    public Integer getHandleTimeFlag() {
        return handleTimeFlag;
    }

    public void setHandleTimeFlag(Integer handleTimeFlag) {
        this.handleTimeFlag = handleTimeFlag;
    }
}
