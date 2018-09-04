package com.thinkgem.jeesite.modules.api.special.entity.vo;

import com.thinkgem.jeesite.modules.api.special.entity.SpecialProduct;

import java.io.Serializable;
import java.util.List;

/**
 * 专项产品包装类
 * @author     ljc
 * @describe   专项产品(相关:专项基础信息,专项关联材料,专项套餐,专项图片)组合对象,完成专项产品的添加.
 * @createTime 2018-8-8 13:39:47
 */
public class SpecialProductVo extends SpecialProduct  implements Serializable {


    private static final long serialVersionUID = 4626400524316350746L;

    private String unitName; // 单位名称
    private String treeTwoName; // 二级科目名称

    private String maxPrice; // 最高价
    private String minPrice; // 最低价

    public String getMaxPrice() {
        return maxPrice;
    }

    public SpecialProductVo setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public SpecialProductVo setMinPrice(String minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    List<MajorCampCategorysVo> majorCampCategorys; // 类别组

    public SpecialProductVo() {
        super();
    }

    public String getUnitName() {
        return unitName;
    }

    public SpecialProductVo setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public String getTreeTwoName() {
        return treeTwoName;
    }

    public SpecialProductVo setTreeTwoName(String treeTwoName) {
        this.treeTwoName = treeTwoName;
        return this;
    }

    public List<MajorCampCategorysVo> getMajorCampCategorys() {
        return majorCampCategorys;
    }

    public SpecialProductVo setMajorCampCategorys(List<MajorCampCategorysVo> majorCampCategorys) {
        this.majorCampCategorys = majorCampCategorys;
        return this;
    }
}
