package com.thinkgem.jeesite.modules.api.special.entity.vo;

import com.thinkgem.jeesite.modules.api.special.entity.SpecialSetMeal;

import java.io.Serializable;

/**
 * 专项套餐包装类
 * @author     ljc
 * @action     删除/更新
 * @createTime 2018-8-8 13:39:47
 */
public class SpecialSetMealVo extends SpecialSetMeal implements Serializable {

    private static final long serialVersionUID = 1024541951561689919L;

    private Integer operationType; // 操作类型备用字段

    private String userNo; // 当前操作用户卡号
    private String dbIP; // 数据行为操作人ID
    private Integer dbCityID; // 地区（事件地区） （默认0）


    public SpecialSetMealVo() {
        super();
    }

    // 有参:设置 访客IP 到包装类
    public SpecialSetMealVo(String dbIP) {
        this.dbIP = dbIP;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public SpecialSetMealVo setOperationType(Integer operationType) {
        this.operationType = operationType;
        return this;
    }

    public String getUserNo() {
        return userNo;
    }

    public SpecialSetMealVo setUserNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    public String getDbIP() {
        return dbIP;
    }

    public SpecialSetMealVo setDbIP(String dbIP) {
        this.dbIP = dbIP;
        return this;
    }

    public Integer getDbCityID() {
        return dbCityID;
    }

    public SpecialSetMealVo setDbCityID(Integer dbCityID) {
        this.dbCityID = dbCityID;
        return this;
    }
}
