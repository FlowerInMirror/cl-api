/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.major.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.major.entity.MajorCampTypeItem;
import com.thinkgem.jeesite.modules.api.major.entity.vo.MajorCampTypeItemVo;

import java.util.List;

/**
 * 主营属性类别项表DAO接口
 * @author ljc
 * @version 2018-08-17
 */
@MyBatisDao
public interface MajorCampTypeItemDao extends CrudDao<MajorCampTypeItem> {

    // "============================== CUD =============================="

    // 主营管理:添加主营属性(类别项),通过实体包装类 @auhtor ljc @createTime 2018-8-18 14:15:26
    int addCategoryItemByEntityVo(MajorCampTypeItemVo majorCampTypeItemVo);

    // 主营管理:更新主营属性(类别项),通过实体包装类 @auhtor ljc @createTime 2018-8-18 14:15:30
    int updateCategoryItemByEntityVo(MajorCampTypeItemVo majorCampTypeItemVo);

    // 主营管理:删除主营属性(类别项),通过实体包装类 @auhtor ljc @createTime 2018-8-18 14:15:33
    int deleteCategoryItemByEntityVo(MajorCampTypeItemVo majorCampTypeItemVo);


    // "============================ FIND/GET ==========================="

    // 主营三段:类型项 @author ljc @createTime 2018-8-17 17:36:06
    List<MajorCampTypeItem> findMajorCampTypeItemListByEntity(MajorCampTypeItem majorCampTypeItem);



}