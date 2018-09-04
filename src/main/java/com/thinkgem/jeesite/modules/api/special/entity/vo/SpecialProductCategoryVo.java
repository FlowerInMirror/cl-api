/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.entity.vo;

import com.thinkgem.jeesite.modules.api.major.entity.MajorCampTypeItem;
import com.thinkgem.jeesite.modules.api.special.entity.SpecialProductCategory;

import java.io.Serializable;
import java.util.List;

/**
 * 专项产品类别表Entity
 * @author ljc
 * @version 2018-08-24
 */
public class SpecialProductCategoryVo extends SpecialProductCategory implements Serializable {

    private static final long serialVersionUID = -133059140050343125L;

    private List<MajorCampTypeItem> categoryItem; // 类别项(包含材料商自定类别项)

    public SpecialProductCategoryVo() {
        super();
    }

    public List<MajorCampTypeItem> getCategoryItem() {
        return categoryItem;
    }

    public SpecialProductCategoryVo setCategoryItem(List<MajorCampTypeItem> categoryItem) {
        this.categoryItem = categoryItem;
        return this;
    }
}