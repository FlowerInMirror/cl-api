/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.major.entity.vo;

import com.thinkgem.jeesite.modules.api.major.entity.MajorCampTypeItem;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 主营属性类别项表VO
 * @author ljc
 * @version 2018-8-18 11:07:54
 */
public class MajorCampTypeItemVo extends MajorCampTypeItem implements Serializable {

    private static final long serialVersionUID = -6510184590912183777L;

    @ApiModelProperty("当前操作用户卡号")
    private String userNo;

    @ApiModelProperty("数据行为操作人ID")
    private String dbIP;

    public MajorCampTypeItemVo () {
        super();
    }

    public MajorCampTypeItemVo(String dbIP) {
        this.dbIP = dbIP;
    }

    public String getUserNo() {
        return userNo;
    }

    public MajorCampTypeItemVo setUserNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    public String getDbIP() {
        return dbIP;
    }

    public MajorCampTypeItemVo setDbIP(String dbIP) {
        this.dbIP = dbIP;
        return this;
    }
}