/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.major.entity.vo;

import com.thinkgem.jeesite.modules.api.major.entity.MajorCampType;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 主营属性类别表VO
 * @author ljc
 * @version 2018-8-18 11:06:33
 */
public class MajorCampTypeVo extends MajorCampType implements Serializable {

    private static final long serialVersionUID = -7298814299023266772L;

    @ApiModelProperty("当前操作用户卡号")
    private String userNo;

    @ApiModelProperty("数据行为操作人IP")
    private String dbIP;

    public MajorCampTypeVo() {
        super();
    }

    public MajorCampTypeVo(String dbIP) {
        this.dbIP = dbIP;
    }

    public String getUserNo() {
        return userNo;
    }

    public MajorCampTypeVo setUserNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    public String getDbIP() {
        return dbIP;
    }

    public MajorCampTypeVo setDbIP(String dbIP) {
        this.dbIP = dbIP;
        return this;
    }
}