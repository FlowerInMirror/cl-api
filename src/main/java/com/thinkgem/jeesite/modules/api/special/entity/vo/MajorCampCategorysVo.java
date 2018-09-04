package com.thinkgem.jeesite.modules.api.special.entity.vo;

import com.thinkgem.jeesite.modules.api.major.entity.MajorCampType;
import com.thinkgem.jeesite.modules.api.major.entity.MajorCampTypeItem;

import java.io.Serializable;
import java.util.List;

/**
 * 主营类别组合包装类 (产品的添加和编辑)
 * @author     ljc
 * @describe   组合对象:当前类别项集
 * @createTime 2018年8月22日13:44:203
 */
public class MajorCampCategorysVo extends MajorCampType implements Serializable {

    private static final long serialVersionUID = -4459822439529761692L;

    private Integer zxUserID; // 专项材料商ID

    private List<MajorCampTypeItem> categoryItem;

    public MajorCampCategorysVo() {
        super();
    }


    public Integer getZxUserID() {
        return zxUserID;
    }

    public MajorCampCategorysVo setZxUserID(Integer zxUserID) {
        this.zxUserID = zxUserID;
        return this;
    }

    public List<MajorCampTypeItem> getCategoryItem() {
        return categoryItem;
    }

    public MajorCampCategorysVo setCategoryItem(List<MajorCampTypeItem> categoryItem) {
        this.categoryItem = categoryItem;
        return this;
    }
}
