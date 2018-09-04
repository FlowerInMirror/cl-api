/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.entity.vo;

import com.thinkgem.jeesite.modules.api.special.entity.MajorCampGroup;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 主营属性组合表VO
 * @author ljc
 * @version 2018-8-18 11:07:47
 */
public class MajorCampGroupVo extends MajorCampGroup implements Serializable {

    private static final long serialVersionUID = -5150250757953092729L;

    @ApiModelProperty("当前操作用户卡号")
    private String userNo;

    @ApiModelProperty("数据行为操作人ID")
    private String dbIP;


    public MajorCampGroupVo(){
        super();
    }

    public MajorCampGroupVo(String dbIP) {
        this.dbIP = dbIP;
    }

    public String getUserNo() {
        return userNo;
    }

    public MajorCampGroupVo setUserNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    public String getDbIP() {
        return dbIP;
    }

    public MajorCampGroupVo setDbIP(String dbIP) {
        this.dbIP = dbIP;
        return this;
    }
}