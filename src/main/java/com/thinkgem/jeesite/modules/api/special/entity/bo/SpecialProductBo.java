/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.entity.bo;

import com.thinkgem.jeesite.modules.api.special.entity.*;

import java.io.Serializable;
import java.util.List;

/**
 * 专项产品业务类
 *
 * @author ljc
 * @action 用于专项产品的CRUD
 * @createTime 2018-8-25 19:33:46
 */
public class SpecialProductBo extends SpecialProduct implements Serializable {

    private static final long serialVersionUID = 3416241153555340369L;
    // 其他
    private String userNo; // 当前操作用户卡号
    private String dbIP; // 数据行为操作人ID

    // 专项图集
    private List<SpecialPicture> specialPictures;

    // 类别组
    private List<SpecialProductCategory> specialProductCategory;

    // 材料商自定义项
    private List<MajorMaterialDealerItem> majorMaterialDealerItems;

    // 组合套餐集
    private List<MajorCampGroup> majorCampGroups;

    public SpecialProductBo() {
        super();
    }

    public SpecialProductBo(String spID) {
        super(spID);
    }

    public List<MajorMaterialDealerItem> getMajorMaterialDealerItems() {
        return majorMaterialDealerItems;
    }

    public SpecialProductBo setMajorMaterialDealerItems(List<MajorMaterialDealerItem> majorMaterialDealerItems) {
        this.majorMaterialDealerItems = majorMaterialDealerItems;
        return this;
    }

    public String getUserNo() {
        return userNo;
    }

    public SpecialProductBo setUserNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    public String getDbIP() {
        return dbIP;
    }

    public SpecialProductBo setDbIP(String dbIP) {
        this.dbIP = dbIP;
        return this;
    }

    public List<SpecialPicture> getSpecialPictures() {
        return specialPictures;
    }

    public SpecialProductBo setSpecialPictures(List<SpecialPicture> specialPictures) {
        this.specialPictures = specialPictures;
        return this;
    }

    public List<SpecialProductCategory> getSpecialProductCategory() {
        return specialProductCategory;
    }

    public SpecialProductBo setSpecialProductCategory(List<SpecialProductCategory> specialProductCategory) {
        this.specialProductCategory = specialProductCategory;
        return this;
    }

    public List<MajorCampGroup> getMajorCampGroups() {
        return majorCampGroups;
    }

    public SpecialProductBo setMajorCampGroups(List<MajorCampGroup> majorCampGroups) {
        this.majorCampGroups = majorCampGroups;
        return this;
    }
}