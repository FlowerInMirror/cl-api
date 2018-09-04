/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.entity.vo;

import com.thinkgem.jeesite.modules.api.special.entity.SpecialPicture;

import java.io.Serializable;

/**
 * 专项图片表Entity
 * @author ljc
 * @version 2018-08-17
 */
public class SpecialPictureVo extends SpecialPicture implements Serializable {

    private static final long serialVersionUID = 1011193862016496125L;

    private String userNo; // 当前操作用户卡号
    private Integer dbCityID; // 数据行为城市(事件地区)
    private String dbIP; // 数据行为操作人ID

    public SpecialPictureVo() {
        super();
    }

    public SpecialPictureVo(String dbIP) {
        this.dbIP = dbIP;
    }

    public String getUserNo() {
        return userNo;
    }

    public SpecialPictureVo setUserNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    public Integer getDbCityID() {
        return dbCityID;
    }

    public SpecialPictureVo setDbCityID(Integer dbCityID) {
        this.dbCityID = dbCityID;
        return this;
    }

    public String getDbIP() {
        return dbIP;
    }

    public SpecialPictureVo setDbIP(String dbIP) {
        this.dbIP = dbIP;
        return this;
    }
}