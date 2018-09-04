/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.entity.vo;

import com.thinkgem.jeesite.modules.api.special.entity.SpecialMatRelevance;

import java.io.Serializable;

/**
 * 专项材料关联包装类
 * @author ljc
 * @version 2018-08-08
 */
public class SpecialMatRelevanceVo extends SpecialMatRelevance implements Serializable {


    private static final long serialVersionUID = 6901529199249025696L;

    private String treeOneName; // 一级类名称
    private String treeTwoName; // 二级类名称
    private String matName; // 材料名称
    private String matSpec; // 材料规格
    private String brandName; // 品牌名称
    private String brandTypeName; // 规格名称

    private String userNo; // 当前操作用户卡号
    private String dbIP; // 数据行为操作人ID
    private Integer dbCityID; // 地区（事件地区） （默认0）

    public SpecialMatRelevanceVo() {
        super();
    }

    // 有参:设置 数据行为操作人IP
    public SpecialMatRelevanceVo(String dbIP) {
        this.dbIP = dbIP;
    }

    public String getTreeOneName() {
        return treeOneName;
    }

    public SpecialMatRelevanceVo setTreeOneName(String treeOneName) {
        this.treeOneName = treeOneName;
        return this;
    }

    public String getTreeTwoName() {
        return treeTwoName;
    }

    public SpecialMatRelevanceVo setTreeTwoName(String treeTwoName) {
        this.treeTwoName = treeTwoName;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public SpecialMatRelevanceVo setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public String getBrandTypeName() {
        return brandTypeName;
    }

    public SpecialMatRelevanceVo setBrandTypeName(String brandTypeName) {
        this.brandTypeName = brandTypeName;
        return this;
    }

    public String getUserNo() {
        return userNo;
    }

    public SpecialMatRelevanceVo setUserNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    public String getMatName() {
        return matName;
    }

    public SpecialMatRelevanceVo setMatName(String matName) {
        this.matName = matName;
        return this;
    }

    public String getMatSpec() {
        return matSpec;
    }

    public SpecialMatRelevanceVo setMatSpec(String matSpec) {
        this.matSpec = matSpec;
        return this;
    }

    public String getDbIP() {
        return dbIP;
    }

    public SpecialMatRelevanceVo setDbIP(String dbIP) {
        this.dbIP = dbIP;
        return this;
    }

    public Integer getDbCityID() {
        return dbCityID;
    }

    public SpecialMatRelevanceVo setDbCityID(Integer dbCityID) {
        this.dbCityID = dbCityID;
        return this;
    }
}